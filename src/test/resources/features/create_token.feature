Feature: Create auth token
  Scenario Outline: verify user can create token
    Given user wants to call "/auth" end point
    And set header "Content-Type" to "application/json"
    And set request body from the file "create_token.json" with username "<username>" and password "<password>"
    And  user perform post call
    Then verify status code is 200
    And verify response message is "Bad credentials"

    Examples:
    |username|password|
    |ADMIN|ADMIN123|