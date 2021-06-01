Feature: As a user, I want to find out the geolocation of the given IP

  Scenario: Verify API returns a valid location to a valid IPv4 IP request
    Given I setup request with a valid IP
    When I make api call
    Then I verify response code to be 200
    And I verify response to be "valid"
#
  Scenario Outline: Verify API returns appropriate error code for invalid IP
    Given I setup request with "<ip>" IP
    When I make api call
    Then I verify response code to be 200
    And I verify response to be "null"
    Examples:
      | ip           |
      | invalid      |
      | malformed    |
      | alphabetical |

  Scenario Outline: Verify API authentication fails if invalid key is provided
    Given I setup a request with "<key>" key
    When I make api call
    Then I verify response code to be 200
    And I verify error response message to be "<error>"
    Examples:
      | key     | error              |
      | invalid | invalid_access_key |
      | empty   | missing_access_key |
