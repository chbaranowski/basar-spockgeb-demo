$(document).ready(function () {

    $("#newUser").click(function () {
        $("#forms").trigger("render.userForm", ["Neuen Nutzer anlegen", {
            basarNumber: "",
            name: "",
            lastname: "",
            email: "",
            links: [
                {href: "../users/"}
            ]}
        ])
    })

    $("#forms").on("render.userForm", function (event, title, user) {
        var source = $("#userFormTemplate").html()
        var template = Handlebars.compile(source)
        var userFormHtml = template({title: title})
        $("#forms").html(userFormHtml)
        $("#name").val(user.name)
        $("#lastname").val(user.lastname)
        $("#email").val(user.email)
        $("#basarNumber").val(user.basarNumber)
        $("#saveUser").click(function () {
            var userData = {
                basarNumber: $("#basarNumber").val(),
                name: $("#name").val(),
                lastname: $("#lastname").val(),
                email: $("#email").val()
            }
            $.ajax({
                url: user.links[0].href,
                type: "POST",
                data: userData,
                success: function () {
                    $('#userForm').modal('hide')
                    $('#userForm').detach()
                    $("#usersBody").trigger("list.users")
                    $("#messageBox").slideUp(300).fadeIn(400).fadeOut(4000)
                    $("#messageBox").html($("<p id=\"successfullCreated\">Kunden erfogreich angelegt</p>"))
                }
            })
        })
        $('#userForm').modal()
    })

    $("#usersBody").on("render.users", function (event, users) {
        var source = $("#usersTemplate").html()
        var template = Handlebars.compile(source)
        $("#usersBody").html(template({"users": users}))
        $(".updateUserButton").click(function () {
            $.ajax({
                url: $(this).val(),
                type: "GET",
                dataType: "json",
                success: function (user) {
                    $("#forms").trigger("render.userForm", ["Bearbeite Nutzer", user])
                }
            })
        })
        $(".deleteUserButton").click(function () {
            $.ajax({
                url: $(this).val(),
                type: "DELETE",
                dataType: "json",
                success: function (user) {
                    $("#usersBody").trigger("list.users")
                }
            })
        })
    })

    $("#usersBody").on("list.users", function () {
        $.ajax({
            url: "../users/",
            type: "GET",
            dataType: "json",
            success: function (users) {
                $("#usersBody").trigger("render.users", [users])
            }
        })
    })

    $("#usersBody").trigger("list.users")

})