package br.com.caelum.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class RotaPedidos {

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {
				
				@Override
				public void configure() throws Exception {
					//noop=true faz com que os arquivos continuem em pedidos
					from("file:pedidos?delay=5s&noop=true").
						log("${id}").
						marshal().xmljson().
						log("${body}").
						//noext faz com que o arquivo n�o fique com a extens�o xml
						setHeader("camelFileName", simple("${file:name.noext}.json")).
					to("file:saida");
				}			
		
		});	
		context.start();
		Thread.sleep(20000);
		context.stop();
		}
}