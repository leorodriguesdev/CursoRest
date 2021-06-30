package com.devleorodrigues.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlaMundoTest {
	
	@Test
	public void testOlaMundo() {
		
		 Response response = request(Method.GET, "http://restapi.wcaquino.me/ola");
		 Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
		 Assert.assertTrue(response.statusCode() == 200);
		 Assert.assertTrue("O status code deveria ser 200", response.statusCode() == 200);
		 Assert.assertEquals(200, response.statusCode());
		 
		 ValidatableResponse validacao = response.then();
		 validacao.statusCode(200);
	}
	
	
	@Test
	public void devoConhecerOutrasFormasRestAssured() {
		
		 Response response = request(Method.GET, "http://restapi.wcaquino.me/ola");
		 ValidatableResponse validacao = response.then();
		 validacao.statusCode(200);
		 
		 get("http://restapi.wcaquino.me/ola").then().statusCode(200);
		 
		 // Melhor modo para fazer o teste /Given-When-Then/Dado-Quando-Entao/
		 
		 given() // pré condições	
		 .when()
		 	.get("http://restapi.wcaquino.me/ola") // A ação de fato 
		 .then() // As assertivas
//		 	.assertThat()
		 	.statusCode(200);
		 
	}
	@Test
	public void devoConhecerMatcherHamcrest() {
		
		Assert.assertThat("128", Matchers.is("128"));       // verificar se 128 é igual a 128 
		Assert.assertThat("maria", Matchers.is("maria"));   // verificar se Maria é igual a Maria 
		Assert.assertThat(128, Matchers.isA(Integer.class));// verificar se 128 é inteiro
		Assert.assertThat(128d, Matchers.isA(Double.class));// verificar se é double
		Assert.assertThat(128d, Matchers.greaterThan(120d));// veririfcar se 128 é maior que 130
 		Assert.assertThat(128d, Matchers.lessThan(130d));   // veririfcar se 128 é menor que 130
		
		List<Integer> impares = Arrays.asList(1,3,5,7,9);   // verificar também com listas 
		assertThat(impares, hasSize(5));                    // verificando se a lista é do tamanho de 5
		assertThat(impares, contains(1,3,5,7,9));           // verificando se contém 1,3,5,7,9 NA ORDEM e deve conter todos elementos da lista
		assertThat(impares, containsInAnyOrder(1,3,5,9,7)); // verificando se contém fora de ordem MAS também deve conter todos elementos da lista
		assertThat(impares, hasItem(1));                    // verificando um elemento apenas
		assertThat(impares, hasItems(1,5));                 // verificando mais de um elemento

		assertThat("Maria", is(not("João")));               // aninhados Fácil leitura
		assertThat("Maria", not("João"));                   // aninhados Fácil leitura is() -> é opcional
		assertThat("Maria", anyOf(is("Maria"), is("Joaquina")));// Pode OU uma opção OU outra -> OU		
		assertThat("Joaquina", allOf(startsWith("Joa"), endsWith("ina"),containsString("qui")));// Pode conter qualquer uma das opções	
		
	}
		 
		 
		 
	@Test
	public void devoValidarBody() {
		 given() // pré condições	
		 .when()
		 	.get("http://restapi.wcaquino.me/ola") // A ação de fato 
		 .then() // As assertivas
		 	.statusCode(200)
		 	.body(is("Ola Mundo!"))
		 	.body(containsString("Mundo"))
		 	.body(is(not(nullValue())));
	}
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 


}

