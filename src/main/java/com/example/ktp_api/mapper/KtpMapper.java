package com.example.ktp_api.mapper;

import com.example.ktp_api.dto.KtpRequestDTO;
import com.example.ktp_api.dto.KtpResponseDTO;
import com.example.ktp_api.entity.Ktp;
import org.springframework.stereotype.Component;

@Component
public class KtpMapper {

    /** RequestDTO → Entity (untuk CREATE) */
    public Ktp toEntity(KtpRequestDTO dto) {
        Ktp ktp = new Ktp();
        ktp.setNomorKtp(dto.getNomorKtp());
        ktp.setNamaLengkap(dto.getNamaLengkap());
        ktp.setAlamat(dto.getAlamat());
        ktp.setTanggalLahir(dto.getTanggalLahir());
        ktp.setJenisKelamin(dto.getJenisKelamin());
        return ktp;
    }

    /** Entity → ResponseDTO (untuk response JSON) */
    public KtpResponseDTO toResponseDTO(Ktp ktp) {
        KtpResponseDTO dto = new KtpResponseDTO();
        dto.setId(ktp.getId());
        dto.setNomorKtp(ktp.getNomorKtp());
        dto.setNamaLengkap(ktp.getNamaLengkap());
        dto.setAlamat(ktp.getAlamat());
        dto.setTanggalLahir(ktp.getTanggalLahir());
        dto.setJenisKelamin(ktp.getJenisKelamin());
        return dto;
    }

    /** Update field entity dari DTO (untuk UPDATE) */
    public void updateEntity(Ktp ktp, KtpRequestDTO dto) {
        ktp.setNomorKtp(dto.getNomorKtp());
        ktp.setNamaLengkap(dto.getNamaLengkap());
        ktp.setAlamat(dto.getAlamat());
        ktp.setTanggalLahir(dto.getTanggalLahir());
        ktp.setJenisKelamin(dto.getJenisKelamin());
    }
}