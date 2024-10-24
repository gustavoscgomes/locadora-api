package com.locarapp.locadora.dto;

import com.locarapp.locadora.enums.Role;

public record RegisterDTO(String username, String password, Role role) {
}
