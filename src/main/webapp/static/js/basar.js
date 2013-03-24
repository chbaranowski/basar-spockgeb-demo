$(document).ready(function () {

    $("#addCartItem").click(function () {
        $(".control-group").removeClass("error")
        $("#messageBox").html($("<p>"))
        var cartItemData = {
            basarNumber: $("#basarNumber").val(),
            price: $("#price").val(),
            description: $("#description").val()
        }
        $.ajax({
            url: "../shopping/cart",
            type: "POST",
            data: cartItemData,
            success: function () {
                $("#basarNumber").val("")
                $("#price").val("")
                $("#description").val("")
                $("#cartBody").trigger("list.cart")
            },
            error: function (data) {
                var response = $.parseJSON(data.responseText)
                $.each(response.errors, function (index, error) {
                    $("#" + error.field + "Group").addClass("error")
                })
                var source = $("#errorsTemplate").html()
                var template = Handlebars.compile(source)
                $("#messageBox").html(template(response))
            }
        })
    })

    $("#cartBody").on("list.cart", function () {
        $.ajax({
            url: "../shopping/cart/",
            type: "GET",
            success: function (shoppingCart) {
                var source = $("#cartTemplate").html()
                var template = Handlebars.compile(source)
                $("#cartBody").html(template(shoppingCart))
                $("#sum").text(shoppingCart.sum)
                $("#total").text(shoppingCart.total)
                $(".deleteCartItemButton").click(function () {
                    $.ajax({
                        url: $(this).val(),
                        type: "DELETE",
                        success: function () {
                            $("#cartBody").trigger("list.cart")
                        }
                    })
                })
            }
        })
    })

    $("#buy").click(function () {
        $.ajax({
            url: "../shopping/",
            type: "POST",
            success: function () {
                $("#cartBody").trigger("list.cart")
                $(".control-group").removeClass("error")
                $("#errorBox").html($("<p>"))
            }
        })
    })

    $("#storno").click(function () {
        $.ajax({
            url: "../shopping/",
            type: "DELETE",
            success: function () {
                $("#cartBody").trigger("list.cart")
                $(".control-group").removeClass("error")
                $("#errorBox").html($("<p>"))
            }
        })

    })

    $("#cartBody").trigger("list.cart")

})