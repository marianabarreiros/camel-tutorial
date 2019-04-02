package br.com.caelum.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpMethods;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.dataformat.xstream.XStreamDataFormat;

import com.thoughtworks.xstream.XStream;
import br.com.caelum.camel.Negociacao;

public class RotaHttpPollingNegociacoes {
	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		final XStream xstream = new XStream();
	    xstream.alias("negociacao", Negociacao.class);
		context.addRoutes(new RouteBuilder() {
				
				@Override
				public void configure() throws Exception {
					from("timer://negociacoes?fixedRate=true&delay=1s&period=360s").
		            to("http4://argentumws.caelum.com.br/negociacoes"). 
		            convertBodyTo(String.class).
		            unmarshal(new XStreamDataFormat(xstream)).
		            split(body()).
		            log("${body}").
		            end();
				}			
		
		});	
		context.start();
		Thread.sleep(20000);
		context.stop();
		}

}
