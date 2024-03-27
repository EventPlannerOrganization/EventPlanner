Feature: Admin Register New Service Provider

  Scenario Outline:Admin Register User successfully
    When Authentication is <username> <password>
    And Name is <firstName> <middleName> <lastName>
    And ContactInfo is <email> <phoneNumber>
    And Address is <country> <city>
    Then User will sign up seccessfully
    Examples:
      | username          | password        | firstName  | middleName | lastName    | email                      | phoneNumber  | country     | city       |
      | 'samerhamdan'     | 'Sam123@@'    | 'samer'     | 'khaled'   | 'hamdan'   | 'hamdann@gmail.com'    | '05943321093' | 'palestine' | 'nablus'    |