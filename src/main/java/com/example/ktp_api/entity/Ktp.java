package com.example.ktp_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ktp")
public class Ktp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nomorKtp", nullable = false, unique = true, length = 16)
    private String nomorKtp;

    @Column(name = "namaLengkap", nullable = false, length = 255)
    private String namaLengkap;

    @Column(name = "alamat", nullable = false, length = 500)
    private String alamat;

    @Column(name = "tanggalLahir", nullable = false)
    private LocalDate tanggalLahir;

    @Column(name = "jenisKelamin", nullable = false, length = 20)
    private String jenisKelamin;
}