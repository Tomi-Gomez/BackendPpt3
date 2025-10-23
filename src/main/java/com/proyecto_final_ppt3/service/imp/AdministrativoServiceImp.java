package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.Model.Administrativo;
import com.proyecto_final_ppt3.Model.Medico;
import com.proyecto_final_ppt3.Repository.AdministrativoRepository;
import com.proyecto_final_ppt3.Repository.MedicoRespository;
import com.proyecto_final_ppt3.controller.request.AdministrativoRequest;
import com.proyecto_final_ppt3.controller.request.MedicoRequest;
import com.proyecto_final_ppt3.controller.response.MedicoResponse;
import com.proyecto_final_ppt3.handler.AdministrativoInsertException;
import com.proyecto_final_ppt3.handler.MedicosNotFoundException;
import com.proyecto_final_ppt3.service.AdministrativoService;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class AdministrativoServiceImp implements AdministrativoService {

    private MedicoRespository medicoRespository;

    private AdministrativoRepository repository;

    private final PasswordEncoder passwordEncoder;

    //Cheuqear si esto esta bien, porque deberia poder
    @Override
    public Administrativo insertarAdmin(AdministrativoRequest administrativoRequest) {
        try {
            administrativoRequest.setContrasenia(passwordEncoder.encode(administrativoRequest.getContrasenia()));
            
            Administrativo admin = AdministrativoRequest.administrativo(administrativoRequest);
            return repository.save(admin);

        }catch (Exception e){
            throw new AdministrativoInsertException("Error al insertar un administrativo" + e.getMessage());
        }
    }

    @Override
    public MedicoResponse insertarMedico(MedicoRequest medicoRequest) {
        try{
            Medico medico = Medico.fromUsuarioRequest(medicoRequest);
            Medico medicoGuardado = medicoRespository.save(medico);
            return MedicoResponse.fromMedico(medicoGuardado);
        }catch (Exception e){
            throw new MedicosNotFoundException("Error al insertar el medico" + e);
        }
    }

     @PostConstruct
    public void initDefaultAdmin() {
        if (repository.count() == 0) {
            Administrativo admin = new Administrativo();
            admin.setNombre("Administrador");
            admin.setApellido("General");
            admin.setDni(12345678);
            admin.setEmail("admin@clinica.com");
            admin.setContrasenia(passwordEncoder.encode("admin123"));
            admin.setTelefono(111111111);
            admin.setTipoUsuario("administrativo");

            repository.save(admin);
            System.out.println("✅ Admin por defecto creado");
        }
    }
}
