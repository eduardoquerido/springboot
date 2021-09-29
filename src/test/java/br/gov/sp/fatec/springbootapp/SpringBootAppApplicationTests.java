package br.gov.sp.fatec.springbootapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springbootapp.controller.RegistrationDto;
import br.gov.sp.fatec.springbootapp.entity.Profile;
import br.gov.sp.fatec.springbootapp.entity.Registration;
import br.gov.sp.fatec.springbootapp.repository.ProfileRepository;
import br.gov.sp.fatec.springbootapp.repository.RegistrationRepository;
import br.gov.sp.fatec.springbootapp.service.ValidationService;

@SpringBootTest
@Transactional
@Rollback
class SpringBootAppApplicationTests {

    @Autowired
    private ProfileRepository profRepo;

    @Autowired
    private RegistrationRepository regRepo;

    @Autowired
    private ValidationService validService;

    private Registration reg1;
    private Profile prof1;

    @BeforeEach()
    void init() {
        profRepo.deleteAll();
        regRepo.deleteAll();

        reg1 = new Registration();
        reg1.setEmail("before@profile.com");
        reg1.setName("before");
        reg1.setPassword("password");
        reg1.setCellphone("999999999");
        regRepo.save(reg1);

        prof1 = new Profile();
        prof1.setAudioHash("qqwwee");
        prof1.setCanvasHash("qqwwee");
        prof1.setWebGLHash("qqwwee");
        prof1.setUserAgent("qqwwee");
        prof1.setFonts("qqwwee");
        prof1.setUuid(UUID.randomUUID().toString());
        prof1.setRegistrations(new HashSet<Registration>());
        prof1.getRegistrations().add(reg1);
        profRepo.save(prof1);
        assertNotNull(prof1.getId());
    }

    @Test
    void registrationRepoSaveTest() {
        Registration reg = new Registration();
        reg.setEmail("profile@profile.com");
        reg.setName("profile");
        reg.setPassword("password");
        reg.setCellphone("999999999");
        regRepo.save(reg);
        assertNotNull(reg.getId());
    }

    @Test
    void profileRepoSaveTest() {
        Profile prof = new Profile();
        prof.setAudioHash("xxyyzz");
        prof.setCanvasHash("xxyyzz");
        prof.setWebGLHash("xxyyzz");
        prof.setUserAgent("xxyyzz");
        prof.setFonts("xxyyzz");
        prof.setUuid(UUID.randomUUID().toString());
        prof.setRegistrations(new HashSet<Registration>());
        Registration reg = new Registration();
        reg.setEmail("profile@profile.com");
        reg.setName("profile");
        reg.setPassword("password");
        reg.setCellphone("999999999");
        regRepo.save(reg);
        prof.getRegistrations().add(reg);
        profRepo.save(prof);
        assertNotNull(prof.getRegistrations().iterator().next().getId());
    }

    @Test
    void profileRepoFindByHashes() {
        assertNotNull(profRepo.findByCanvasHashOrWebGLHashOrAudioHash("qqwwee", "qqwwee", "qqwwee").getId());
    }

    @Test
    void registrationRepoFindByEmail() {
        Registration reg = new Registration();
        reg.setEmail("profile@profile.com");
        reg.setName("profile");
        reg.setPassword("password");
        reg.setCellphone("999999999");
        regRepo.save(reg);

        assertNotNull(regRepo.findByEmail("profile@profile.com").getId());
    }

    @Test
    void validationServiceParamsException() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setEmail("");
        registrationDto.setPassword("password");
        registrationDto.setName("profile");
        registrationDto.setCellphone("999999999");
        registrationDto.setAudioHash("xxxyyyzzz");
        registrationDto.setCanvasHash("xxxyyyzzz");
        registrationDto.setWebGLHash("xxxyyyzzz");
        registrationDto.setUserAgent("xxxyyyzzz");
        registrationDto.setFonts("xxxyyyzzz");
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> validService.createRegistration(registrationDto));
        assertTrue(runtimeException.getMessage().equals("Invalid params"));
    }

    @Test
    void validationServiceDuplicateRegistration() {
        Registration reg = new Registration();
        reg.setEmail("profile@profile.com");
        reg.setName("profile");
        reg.setPassword("password");
        reg.setCellphone("999999999");
        regRepo.save(reg);

        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setEmail("profile@profile.com");
        registrationDto.setPassword("password");
        registrationDto.setName("profile");
        registrationDto.setCellphone("999999999");
        registrationDto.setAudioHash("xxxyyyzzz");
        registrationDto.setCanvasHash("xxxyyyzzz");
        registrationDto.setWebGLHash("xxxyyyzzz");
        registrationDto.setUserAgent("xxxyyyzzz");
        registrationDto.setFonts("xxxyyyzzz");

        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> validService.createRegistration(registrationDto));
        assertTrue(runtimeException.getMessage().equals("The email address must be unique"));
    }

    @Test
    void validationServiceCreateRegistration() {
        String hash = "xxxyyyzzz";
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setEmail("profile@profile");
        registrationDto.setPassword("password");
        registrationDto.setName("profile");
        registrationDto.setCellphone("999999999");
        registrationDto.setAudioHash(hash);
        registrationDto.setCanvasHash(hash);
        registrationDto.setWebGLHash(hash);
        registrationDto.setUserAgent(hash);
        registrationDto.setFonts(hash);
        assertNotNull(validService.createRegistration(registrationDto).getId());
    }

    @Test
    void validationServiceExistHashes() {
        String hash = "xxxyyyzzz";
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setEmail("profile@profile");
        registrationDto.setPassword("password");
        registrationDto.setName("profile");
        registrationDto.setCellphone("999999999");
        registrationDto.setAudioHash(hash);
        registrationDto.setCanvasHash(hash);
        registrationDto.setWebGLHash(hash);
        registrationDto.setUserAgent(hash);
        registrationDto.setFonts(hash);

        RegistrationDto registrationDto2 = new RegistrationDto();
        registrationDto2.setEmail("profile2@profile2.com");
        registrationDto2.setPassword("password");
        registrationDto2.setName("profile");
        registrationDto2.setCellphone("999999999");
        registrationDto2.setAudioHash(hash);
        registrationDto2.setCanvasHash(hash);
        registrationDto2.setWebGLHash(hash);
        registrationDto2.setUserAgent(hash);
        registrationDto2.setFonts(hash);

        validService.createRegistration(registrationDto);
        validService.createRegistration(registrationDto2);
        assertEquals(regRepo.findByOneOfHashes(hash).size(), 2);
    }

    @Test
    void validationServiceFindProfileException() {
        Long id = 12345678910L;
        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> validService.findProfileById(id));
        assertTrue(runtimeException.getMessage().equals("Profile not found for id: " + id));
    }

    @Test
    void validationServiceFindProfileById() {
        assertNotNull(validService.findProfileById(prof1.getId()));
    }

    @Test
    void validationServiceFindProfileByHash() {
        assertNotNull(validService.findProfileByHash(prof1.getAudioHash()));
    }

    @Test 
    void validationServiceFindRegistrationException() {
        Long id = 12345678910L;
        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> validService.findRegistrationById(id));
        assertTrue(runtimeException.getMessage().equals("Registration not found for id: " + id));
    }

    @Test
    void validationServiceFindRegistrationById() {
        assertNotNull(validService.findRegistrationById(reg1.getId()));
    }

    @Test
    void validationServiceDeleteProfile() {
        assertNotNull(validService.deleteProfile(prof1.getId()));
    }

    @Test
    void validationServiceDeleteRegistration() {
        assertNotNull(validService.deleteProfile(prof1.getId()));
        assertNotNull(validService.deleteRegistration(reg1.getId()));
    }

    @Test
    void validationServiceUpdateRegistration() {
        RegistrationDto registration = new RegistrationDto();
        registration.setName("fabricio");
        assertEquals(validService.updateRegistration(registration, reg1.getId()).getName(), "fabricio");
    }

    @AfterEach
    void exit() {
        regRepo.deleteAll();
        profRepo.deleteAll();
    }
}
