package com.example.mock_stock.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.mock_stock.domain.model.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryITest {

    @Autowired
    UserRepository userRepository;

    @Test
    void save_and_find_user() {
        User user = new User();
        user.setUsername("integration_user");
        user.setBalance(new BigDecimal("1000"));
        user.setCreatedAt(LocalDateTime.now());
        User saved = userRepository.save(user);

        assertThat(saved.getId()).isNotNull();
        assertThat(userRepository.findById(saved.getId())).isPresent();
    }

    @Test
    void save_and_find_by_username_and_id() {
        User user = new User();
        user.setUsername("integration_user");
        user.setBalance(new BigDecimal("1000"));
        user.setCreatedAt(LocalDateTime.now());
        User saved = userRepository.save(user);

        assertThat(saved.getId()).isNotNull();

        // username 기반 조회
        User foundByUsername = userRepository.findByUsername("integration_user").orElse(null);
        assertThat(foundByUsername).isNotNull();
        assertThat(foundByUsername.getId()).isEqualTo(saved.getId());

        // id 기반 조회
        User foundById = userRepository.findById(saved.getId()).orElse(null);
        assertThat(foundById).isNotNull();
        assertThat(foundById.getUsername()).isEqualTo("integration_user");
    }
}
