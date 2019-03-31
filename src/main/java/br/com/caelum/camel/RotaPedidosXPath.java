package br.com.caelum.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class RotaPedidosXPath {
	
	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {
				
				@Override
				public void configure() throws Exception {
					//noop=true faz com que os arquivos continuem em pedidos
					from("file:pedidos?delay=5s&noop=true").
						split().
							xpath("pedido/itens/item").
						filter().
							xpath("item/formato[text()='EBOOK']").
						log("${id} - ${body}").
						//noext faz com que o arquivo não fique com a extensão xml
						setHeader("camelFileName", simple("${file:name.noext}.json")).
					to("http4://localhost:8080/webservices/ebook/item");
				}			
		
		});	
		context.start();
		Thread.sleep(20000);
		context.stop();
		}

}
