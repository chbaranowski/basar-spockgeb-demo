package basar;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import ro.isdc.wro.config.jmx.ConfigConstants;
import ro.isdc.wro.http.ConfigurableWroFilter;
import ro.isdc.wro.manager.factory.ConfigurableWroManagerFactory;
import ro.isdc.wro.model.factory.WroModelFactory;
import ro.isdc.wro.model.factory.XmlModelFactory;
import ro.isdc.wro.model.resource.processor.factory.ConfigurableProcessorsFactory;

@Configuration
public class Wro4jConfiguration {
	private static final Logger log = LoggerFactory.getLogger(Wro4jConfiguration.class);

	@Bean
	FilterRegistrationBean webResourceOptimizer(Environment env) {
		FilterRegistrationBean fr = new FilterRegistrationBean();
		ConfigurableWroFilter filter = new ConfigurableWroFilter();
		Properties props = buildWroProperties(env);
		filter.setProperties(props);
		filter.setWroManagerFactory(new Wro4jCustomXmlModelManagerFactory(props));
		fr.setFilter(filter);
		fr.addUrlPatterns("/static/*");
		return fr;
	}

	private static final String[] OTHER_WRO_PROP = new String[] { ConfigurableProcessorsFactory.PARAM_PRE_PROCESSORS,
			ConfigurableProcessorsFactory.PARAM_POST_PROCESSORS };

	private Properties buildWroProperties(Environment env) {
		Properties prop = new Properties();
		for (ConfigConstants c : ConfigConstants.values()) {
			addProperty(env, prop, c.name());
		}
		for (String name : OTHER_WRO_PROP) {
			addProperty(env, prop, name);
		}
		log.debug("WRO4J properties {}", prop);
		return prop;
	}

	private void addProperty(Environment env, Properties to, String name) {
		String value = env.getProperty("wro." + name);
		if (value != null) {
			to.put(name, value);
		}
	}
}

class Wro4jCustomXmlModelManagerFactory extends ConfigurableWroManagerFactory {
	private static final Logger log = LoggerFactory.getLogger(Wro4jCustomXmlModelManagerFactory.class);

	final private Properties props;

	public Wro4jCustomXmlModelManagerFactory(Properties props) {
		this.props = props;
	}

	@Override
	protected Properties newConfigProperties() {
		return props;
	}

	@Override
	protected WroModelFactory newModelFactory() {
		log.debug("loading from /wro.xml");
		return new XmlModelFactory() {
			@Override
			protected InputStream getModelResourceAsStream() throws IOException {
				String resourceLocation = "/wro.xml";
				final InputStream stream = getClass().getResourceAsStream(resourceLocation);

				if (stream == null) {
					throw new IOException("Invalid resource requested: " + resourceLocation);
				}

				return stream;
			}
		};
	}

}

