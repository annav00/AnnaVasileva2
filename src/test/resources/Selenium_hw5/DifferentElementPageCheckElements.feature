Feature: Different elements on "Different Elements Page" are clickable and logs panel shows user action

  Scenario: Do interaction with checkboxes, radio and dropdown
    Given I open JDI GitHub site
    And I login as user "Roman Iovlev"
    Then "Home Page" page should be opened
    And Username is "ROMAN IOVLEV"
    When I click on "Service" button in Header
    And I click on "Different elements" button in Service dropdown
    And I select checkBoxes
      | Water |
      | Wind  |
    And I select radio buttons "Selen"
    And I select dropdown "Yellow"
    Then I see in Log section
      | Colors: value changed to Yellow |
      | metal: value changed to Selen |
      | Wind: condition changed to true |
      | Water: condition changed to true |