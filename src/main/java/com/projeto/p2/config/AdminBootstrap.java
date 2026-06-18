package com.projeto.p2.config;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminBootstrap {

    @Bean
    public CommandLineRunner seedAdmin(DataSource dataSource, PasswordEncoder encoder) {
        return args -> {
            JdbcTemplate jdbc = new JdbcTemplate(dataSource);
            Integer existing = jdbc.queryForObject(
                "SELECT COUNT(*) FROM users WHERE email = ?", Integer.class, "admin@groovestore.com");
            if (existing != null && existing == 0) {
                jdbc.update(
                    "INSERT INTO users(nome, email, senha) VALUES (?, ?, ?)",
                    "admin", "admin@groovestore.com", encoder.encode("admin123"));
            }
            jdbc.update(
                "INSERT INTO perfil(userid, cargo) " +
                "SELECT id, 'admin' FROM users WHERE email = 'admin@groovestore.com' " +
                "ON CONFLICT (userid) DO UPDATE SET cargo = 'admin'");

            Integer qtdProdutos = jdbc.queryForObject(
                "SELECT COUNT(*) FROM produto", Integer.class);
            if (qtdProdutos != null && qtdProdutos == 0) {
                Object[][] produtos = {
                    {"Taylor Swift", "The Tortured Poets Department", "Vinil", "Pop Internacional", "289,90", "2024",
                     "https://upload.wikimedia.org/wikipedia/en/1/1a/Taylor_Swift_-_The_Tortured_Poets_Department.png"},
                    {"Madonna", "Like a Prayer", "Vinil", "Pop Internacional", "349,90", "1989",
                     "https://upload.wikimedia.org/wikipedia/en/6/64/Madonna_like_a_prayer.png"},
                    {"Dua Lipa", "Future Nostalgia", "CD", "Dance", "79,90", "2020",
                     "https://upload.wikimedia.org/wikipedia/en/f/f5/Dua_Lipa_-_Future_Nostalgia_%28Official_Album_Cover%29.png"},
                    {"Michael Jackson", "Thriller", "Vinil", "Pop Internacional", "529,90", "1982",
                     "https://upload.wikimedia.org/wikipedia/en/5/55/Michael_Jackson_-_Thriller.png"},
                    {"Beyonce", "Lemonade", "CD", "R&B / Soul", "89,90", "2016",
                     "https://upload.wikimedia.org/wikipedia/en/5/52/Beyonce_-_Lemonade_%28Official_Album_Cover%29.png"},
                    {"ABBA", "Gold - Greatest Hits", "Vinil", "Pop Internacional", "299,90", "1992",
                     "https://upload.wikimedia.org/wikipedia/en/2/2e/ABBA_-_Gold.png"}
                };
                for (Object[] p : produtos) {
                    jdbc.update(
                        "INSERT INTO produto(artista,album,formato,genero,preco,ano,imagem) VALUES (?,?,?,?,?,?,?)",
                        p[0], p[1], p[2], p[3], p[4], p[5], p[6]);
                }
            }
        };
    }
}
