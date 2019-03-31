package br.com.caelum.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class RotaPedidosJsonPath {
	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {
				
				@Override
				public void configure() throws Exception {
					//noop=true faz com que os arquivos continuem em pedidos
					from("file:pedidosJson?delay=5s&noop=true").
					//Foi necessário adicionar o jsonpath no pom.xml.
						setProperty("enabled").jsonpath("$.errorHandling.enabled").
						setProperty("httpErrorCode").jsonpath("$.errorHandling.errorCodes[*].httpErrorCode").
						setProperty("business").jsonpath("$.errorHandling.errorCodes[*].business[*].enabled").
						setProperty("retry").jsonpath("$.errorHandling.errorCodes[*].business[*].retry.enabled").
						log("Error Hadling: ${property.enabled}").
						log("HttpErrorCode: ${property.httpErrorCode}").
						log("business: ${property.business}").
						log("retry: ${property.retry}").
						//noext faz com que o arquivo não fique com a extensão xml
						//setHeader("camelFileName", simple("${file:name.noext}.json")).
					to("file:saida");
				}			
		
		});	
		context.start();
		Thread.sleep(20000);
		context.stop();
		}
}
