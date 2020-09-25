package com.finrademo;

import io.restassured.internal.assertion.Assertion;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * @author Vijay Krishna
 * 
 * 
 */

/**
 * API Testing for Deck of Cards.
 */
public class DeckOfCardsAPITest {

	private static final String DECK_OF_API_ROOT_URL = "https://deckofcardsapi.com";

	/**
	 * Test for create a new deck without shuffle
	 */
	@Test
	public void testCreateNewDeck() {
		given().when().get(DECK_OF_API_ROOT_URL + "/api/deck/new").then().assertThat().contentType("application/json")
				.statusCode(200).body("success", equalTo(true)).body("deck_id", notNullValue())
				.body("remaining", equalTo(52)).body("shuffled", equalTo(false));

	}

	/**
	 * Testing for creating a new Deck with shuffle
	 */
	@Test
	public void testCreateNewDeckWithShuffle() {
		given().when().get(DECK_OF_API_ROOT_URL + "/api/deck/new/shuffle").then().assertThat()
				.contentType("application/json").statusCode(200).body("success", equalTo(true))
				.body("deck_id", notNullValue()).body("remaining", equalTo(52)).body("shuffled", equalTo(true));

	}

	/**
	 * Testing for adding jokers to a new deck without shuffle
	 */
	@Test
	public void testAddingJockersInNewDeck() {
		given().when().param("jokers_enabled", true).get(DECK_OF_API_ROOT_URL + "/api/deck/new").then().assertThat()
				.contentType("application/json").statusCode(200).body("success", equalTo(true))
				.body("deck_id", notNullValue()).body("remaining", equalTo(54)).body("shuffled", equalTo(false));
	}

	/**
	 * Testing for adding jokers to new Deck with shuffle
	 */
	@Test
	public void testAddingJockersInNewDeckWithShuffle() {
		given().when().param("jokers_enabled", true).get(DECK_OF_API_ROOT_URL + "/api/deck/new/shuffle").then()
				.assertThat().contentType("application/json").statusCode(200).body("success", equalTo(true))
				.body("deck_id", notNullValue()).body("remaining", equalTo(54)).body("shuffled", equalTo(true));
	}

	/**
	 * Testing for draw a card from new deck without shuffle
	 */
	@Test
	public void testDrawAOneCardFromDeck() {
		Response response = given().when().get(DECK_OF_API_ROOT_URL + "/api/deck/new");
		Assertions.assertEquals(200, response.getStatusCode());
		String deckId = response.jsonPath().getString("deck_id");
		Assertions.assertNotNull(deckId);

		// Drawing a new deck card
		given().when().pathParam("deck_id", deckId).get(DECK_OF_API_ROOT_URL + "/api/deck/{deck_id}/draw/").then()
				.statusCode(200).body("success", equalTo(true)).body("deck_id", equalTo(deckId))
				.body("cards[0].code", equalTo("AS")).body("cards[0].value", equalTo("ACE"))
				.body("cards[0].suit", equalTo("SPADES")).body("remaining", equalTo(51));
	}

	/**
	 * Testing for draw multiple cards from a new deck of cards without shuffle
	 */
	@Test
	public void testDrawMultipleCardsFromNewDeck() {
		Response response = given().when().get(DECK_OF_API_ROOT_URL + "/api/deck/new");
		Assertions.assertEquals(200, response.getStatusCode());
		String deckId = response.jsonPath().getString("deck_id");
		Assertions.assertNotNull(deckId);

		// Drawing a new deck card
		given().when().pathParam("deck_id", deckId).param("count", 2)
				.get(DECK_OF_API_ROOT_URL + "/api/deck/{deck_id}/draw/").then().statusCode(200)
				.body("success", equalTo(true)).body("deck_id", equalTo(deckId)).body("cards[0].code", equalTo("AS"))
				.body("cards[0].value", equalTo("ACE")).body("cards[0].suit", equalTo("SPADES"))
				.body("cards[1].code", equalTo("2S")).body("cards[1].value", equalTo("2"))
				.body("cards[1].suit", equalTo("SPADES")).body("remaining", equalTo(50));
	}

	/**
	 * Testing for draw a card from new deck with shuffle
	 */
	@Test
	public void testDrawAOneCardFromDeckWithShuffle() {
		Response response = given().when().get(DECK_OF_API_ROOT_URL + "/api/deck/new/shuffle");
		Assertions.assertEquals(200, response.getStatusCode());
		String deckId = response.jsonPath().getString("deck_id");
		Assertions.assertNotNull(deckId);

		// Drawing a new deck card
		given().when().pathParam("deck_id", deckId).get(DECK_OF_API_ROOT_URL + "/api/deck/{deck_id}/draw/").then()
				.statusCode(200).body("success", equalTo(true)).body("deck_id", equalTo(deckId))
				.body("cards", hasSize(1)).body("cards[0].code", notNullValue()).body("cards[0].value", notNullValue())
				.body("cards[0].suit", notNullValue()).body("remaining", equalTo(51));
	}

	/**
	 * Testing for draw multiple cards from a new deck of cards with shuffle
	 */
	@Test
	public void testDrawMultipleCardsFromNewDeckWithShuffle() {
		Response response = given().when().get(DECK_OF_API_ROOT_URL + "/api/deck/new/shuffle");
		Assertions.assertEquals(200, response.getStatusCode());
		String deckId = response.jsonPath().getString("deck_id");
		Assertions.assertNotNull(deckId);

		// Drawing a new deck card
		given().when().pathParam("deck_id", deckId).param("count", 2)
				.get(DECK_OF_API_ROOT_URL + "/api/deck/{deck_id}/draw/").then().statusCode(200)
				.body("success", equalTo(true)).body("deck_id", equalTo(deckId)).body("cards", hasSize(2))
				.body("cards[0].code", notNullValue()).body("cards[0].value", notNullValue())
				.body("cards[0].suit", notNullValue()).body("cards[1].code", notNullValue())
				.body("cards[1].value", notNullValue()).body("cards[1].suit", notNullValue())
				.body("remaining", equalTo(50));
	}
}
