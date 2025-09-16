#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
# iid: 1 
# labels: sprint_backlog
# assignee: dev_a
# due_date: 2025-02-23
# time_estimate: 6h
@E2E
Feature: si je recherche selenium dans le moteur de recherche de dawan je trouve les bons prix et les bonnes formations

  Scenario Outline: checker les prix
    Given connexion sur la page d acceuil
    When j entre selenium dans le moteur
    And j appuie sur le bouton de recherche
    Then les prix <price> sont les bons
    
    Examples: 
      | price |
      | 1432  |

