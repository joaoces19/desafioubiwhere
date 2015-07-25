/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ubiwhere.business_logic.responses;

/**
 *
 * @author joaoces
 */
public enum PersonResponse {
    OK,
    PERSON_NAME_ALREADY_EXISTS,
    PERSON_NUMBER_ALREADY_EXISTS,
    PERSON_NUMBER_DOESNT_EXIST,
    PERSON_ALREADY_LINKED,
    PERSON_NOT_LINKED_TO_COMPANY,
    INVALID_PERSON_ID,
    INVALID_COMPANY_ID    
}
