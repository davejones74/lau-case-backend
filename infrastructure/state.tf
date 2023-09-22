terraform {
  backend "azurerm" {}
  required_providers {
    azuread = {
      source  = "hashicorp/azuread"
      version = "2.43.0"
    }
  }
}
