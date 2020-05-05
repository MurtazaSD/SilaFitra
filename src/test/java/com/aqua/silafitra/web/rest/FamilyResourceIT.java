package com.aqua.silafitra.web.rest;

import com.aqua.silafitra.SilaFitraApp;
import com.aqua.silafitra.domain.Family;
import com.aqua.silafitra.repository.FamilyRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link FamilyResource} REST controller.
 */
@SpringBootTest(classes = SilaFitraApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class FamilyResourceIT {

    private static final Long DEFAULT_HOF_EJAMAAT_ID = 1L;
    private static final Long UPDATED_HOF_EJAMAAT_ID = 2L;

    private static final String DEFAULT_HOF_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_HOF_FULL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final Integer DEFAULT_TOTAL_GENTS = 1;
    private static final Integer UPDATED_TOTAL_GENTS = 2;

    private static final Integer DEFAULT_TOTAL_LADIES = 1;
    private static final Integer UPDATED_TOTAL_LADIES = 2;

    private static final Integer DEFAULT_TOTAL_KIDS = 1;
    private static final Integer UPDATED_TOTAL_KIDS = 2;

    private static final Integer DEFAULT_TOTAL_PREGNANTS = 1;
    private static final Integer UPDATED_TOTAL_PREGNANTS = 2;

    private static final Integer DEFAULT_TOTAL_DECESEAD = 1;
    private static final Integer UPDATED_TOTAL_DECESEAD = 2;

    private static final Float DEFAULT_ZAKATUL_FITR = 1F;
    private static final Float UPDATED_ZAKATUL_FITR = 2F;

    private static final Float DEFAULT_NAJWATUSUKR = 1F;
    private static final Float UPDATED_NAJWATUSUKR = 2F;

    private static final Float DEFAULT_KHUMUS = 1F;
    private static final Float UPDATED_KHUMUS = 2F;

    private static final Float DEFAULT_SILATUL_IMAM = 1F;
    private static final Float UPDATED_SILATUL_IMAM = 2F;

    private static final Float DEFAULT_NAZRUL_MAQAM = 1F;
    private static final Float UPDATED_NAZRUL_MAQAM = 2F;

    private static final LocalDate DEFAULT_LAST_UPDATE_TSTAMP = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_UPDATE_TSTAMP = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SUBMITTED_BY = "AAAAAAAAAA";
    private static final String UPDATED_SUBMITTED_BY = "BBBBBBBBBB";

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFamilyMockMvc;

    private Family family;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Family createEntity(EntityManager em) {
        Family family = new Family()
            .hofEjamaatId(DEFAULT_HOF_EJAMAAT_ID)
            .hofFullName(DEFAULT_HOF_FULL_NAME)
            .emailAddress(DEFAULT_EMAIL_ADDRESS)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .totalGents(DEFAULT_TOTAL_GENTS)
            .totalLadies(DEFAULT_TOTAL_LADIES)
            .totalKids(DEFAULT_TOTAL_KIDS)
            .totalPregnants(DEFAULT_TOTAL_PREGNANTS)
            .totalDecesead(DEFAULT_TOTAL_DECESEAD)
            .zakatulFitr(DEFAULT_ZAKATUL_FITR)
            .najwatusukr(DEFAULT_NAJWATUSUKR)
            .khumus(DEFAULT_KHUMUS)
            .silatulImam(DEFAULT_SILATUL_IMAM)
            .nazrulMaqam(DEFAULT_NAZRUL_MAQAM)
            .lastUpdateTstamp(DEFAULT_LAST_UPDATE_TSTAMP)
            .submittedBy(DEFAULT_SUBMITTED_BY);
        return family;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Family createUpdatedEntity(EntityManager em) {
        Family family = new Family()
            .hofEjamaatId(UPDATED_HOF_EJAMAAT_ID)
            .hofFullName(UPDATED_HOF_FULL_NAME)
            .emailAddress(UPDATED_EMAIL_ADDRESS)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .totalGents(UPDATED_TOTAL_GENTS)
            .totalLadies(UPDATED_TOTAL_LADIES)
            .totalKids(UPDATED_TOTAL_KIDS)
            .totalPregnants(UPDATED_TOTAL_PREGNANTS)
            .totalDecesead(UPDATED_TOTAL_DECESEAD)
            .zakatulFitr(UPDATED_ZAKATUL_FITR)
            .najwatusukr(UPDATED_NAJWATUSUKR)
            .khumus(UPDATED_KHUMUS)
            .silatulImam(UPDATED_SILATUL_IMAM)
            .nazrulMaqam(UPDATED_NAZRUL_MAQAM)
            .lastUpdateTstamp(UPDATED_LAST_UPDATE_TSTAMP)
            .submittedBy(UPDATED_SUBMITTED_BY);
        return family;
    }

    @BeforeEach
    public void initTest() {
        family = createEntity(em);
    }

    @Test
    @Transactional
    public void createFamily() throws Exception {
        int databaseSizeBeforeCreate = familyRepository.findAll().size();

        // Create the Family
        restFamilyMockMvc.perform(post("/api/families")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(family)))
            .andExpect(status().isCreated());

        // Validate the Family in the database
        List<Family> familyList = familyRepository.findAll();
        assertThat(familyList).hasSize(databaseSizeBeforeCreate + 1);
        Family testFamily = familyList.get(familyList.size() - 1);
        assertThat(testFamily.getHofEjamaatId()).isEqualTo(DEFAULT_HOF_EJAMAAT_ID);
        assertThat(testFamily.getHofFullName()).isEqualTo(DEFAULT_HOF_FULL_NAME);
        assertThat(testFamily.getEmailAddress()).isEqualTo(DEFAULT_EMAIL_ADDRESS);
        assertThat(testFamily.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testFamily.getTotalGents()).isEqualTo(DEFAULT_TOTAL_GENTS);
        assertThat(testFamily.getTotalLadies()).isEqualTo(DEFAULT_TOTAL_LADIES);
        assertThat(testFamily.getTotalKids()).isEqualTo(DEFAULT_TOTAL_KIDS);
        assertThat(testFamily.getTotalPregnants()).isEqualTo(DEFAULT_TOTAL_PREGNANTS);
        assertThat(testFamily.getTotalDecesead()).isEqualTo(DEFAULT_TOTAL_DECESEAD);
        assertThat(testFamily.getZakatulFitr()).isEqualTo(DEFAULT_ZAKATUL_FITR);
        assertThat(testFamily.getNajwatusukr()).isEqualTo(DEFAULT_NAJWATUSUKR);
        assertThat(testFamily.getKhumus()).isEqualTo(DEFAULT_KHUMUS);
        assertThat(testFamily.getSilatulImam()).isEqualTo(DEFAULT_SILATUL_IMAM);
        assertThat(testFamily.getNazrulMaqam()).isEqualTo(DEFAULT_NAZRUL_MAQAM);
        assertThat(testFamily.getLastUpdateTstamp()).isEqualTo(DEFAULT_LAST_UPDATE_TSTAMP);
        assertThat(testFamily.getSubmittedBy()).isEqualTo(DEFAULT_SUBMITTED_BY);
    }

    @Test
    @Transactional
    public void createFamilyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = familyRepository.findAll().size();

        // Create the Family with an existing ID
        family.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFamilyMockMvc.perform(post("/api/families")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(family)))
            .andExpect(status().isBadRequest());

        // Validate the Family in the database
        List<Family> familyList = familyRepository.findAll();
        assertThat(familyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkHofEjamaatIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = familyRepository.findAll().size();
        // set the field null
        family.setHofEjamaatId(null);

        // Create the Family, which fails.

        restFamilyMockMvc.perform(post("/api/families")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(family)))
            .andExpect(status().isBadRequest());

        List<Family> familyList = familyRepository.findAll();
        assertThat(familyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFamilies() throws Exception {
        // Initialize the database
        familyRepository.saveAndFlush(family);

        // Get all the familyList
        restFamilyMockMvc.perform(get("/api/families?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(family.getId().intValue())))
            .andExpect(jsonPath("$.[*].hofEjamaatId").value(hasItem(DEFAULT_HOF_EJAMAAT_ID.intValue())))
            .andExpect(jsonPath("$.[*].hofFullName").value(hasItem(DEFAULT_HOF_FULL_NAME)))
            .andExpect(jsonPath("$.[*].emailAddress").value(hasItem(DEFAULT_EMAIL_ADDRESS)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].totalGents").value(hasItem(DEFAULT_TOTAL_GENTS)))
            .andExpect(jsonPath("$.[*].totalLadies").value(hasItem(DEFAULT_TOTAL_LADIES)))
            .andExpect(jsonPath("$.[*].totalKids").value(hasItem(DEFAULT_TOTAL_KIDS)))
            .andExpect(jsonPath("$.[*].totalPregnants").value(hasItem(DEFAULT_TOTAL_PREGNANTS)))
            .andExpect(jsonPath("$.[*].totalDecesead").value(hasItem(DEFAULT_TOTAL_DECESEAD)))
            .andExpect(jsonPath("$.[*].zakatulFitr").value(hasItem(DEFAULT_ZAKATUL_FITR.doubleValue())))
            .andExpect(jsonPath("$.[*].najwatusukr").value(hasItem(DEFAULT_NAJWATUSUKR.doubleValue())))
            .andExpect(jsonPath("$.[*].khumus").value(hasItem(DEFAULT_KHUMUS.doubleValue())))
            .andExpect(jsonPath("$.[*].silatulImam").value(hasItem(DEFAULT_SILATUL_IMAM.doubleValue())))
            .andExpect(jsonPath("$.[*].nazrulMaqam").value(hasItem(DEFAULT_NAZRUL_MAQAM.doubleValue())))
            .andExpect(jsonPath("$.[*].lastUpdateTstamp").value(hasItem(DEFAULT_LAST_UPDATE_TSTAMP.toString())))
            .andExpect(jsonPath("$.[*].submittedBy").value(hasItem(DEFAULT_SUBMITTED_BY)));
    }
    
    @Test
    @Transactional
    public void getFamily() throws Exception {
        // Initialize the database
        familyRepository.saveAndFlush(family);

        // Get the family
        restFamilyMockMvc.perform(get("/api/families/{id}", family.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(family.getId().intValue()))
            .andExpect(jsonPath("$.hofEjamaatId").value(DEFAULT_HOF_EJAMAAT_ID.intValue()))
            .andExpect(jsonPath("$.hofFullName").value(DEFAULT_HOF_FULL_NAME))
            .andExpect(jsonPath("$.emailAddress").value(DEFAULT_EMAIL_ADDRESS))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.totalGents").value(DEFAULT_TOTAL_GENTS))
            .andExpect(jsonPath("$.totalLadies").value(DEFAULT_TOTAL_LADIES))
            .andExpect(jsonPath("$.totalKids").value(DEFAULT_TOTAL_KIDS))
            .andExpect(jsonPath("$.totalPregnants").value(DEFAULT_TOTAL_PREGNANTS))
            .andExpect(jsonPath("$.totalDecesead").value(DEFAULT_TOTAL_DECESEAD))
            .andExpect(jsonPath("$.zakatulFitr").value(DEFAULT_ZAKATUL_FITR.doubleValue()))
            .andExpect(jsonPath("$.najwatusukr").value(DEFAULT_NAJWATUSUKR.doubleValue()))
            .andExpect(jsonPath("$.khumus").value(DEFAULT_KHUMUS.doubleValue()))
            .andExpect(jsonPath("$.silatulImam").value(DEFAULT_SILATUL_IMAM.doubleValue()))
            .andExpect(jsonPath("$.nazrulMaqam").value(DEFAULT_NAZRUL_MAQAM.doubleValue()))
            .andExpect(jsonPath("$.lastUpdateTstamp").value(DEFAULT_LAST_UPDATE_TSTAMP.toString()))
            .andExpect(jsonPath("$.submittedBy").value(DEFAULT_SUBMITTED_BY));
    }

    @Test
    @Transactional
    public void getNonExistingFamily() throws Exception {
        // Get the family
        restFamilyMockMvc.perform(get("/api/families/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFamily() throws Exception {
        // Initialize the database
        familyRepository.saveAndFlush(family);

        int databaseSizeBeforeUpdate = familyRepository.findAll().size();

        // Update the family
        Family updatedFamily = familyRepository.findById(family.getId()).get();
        // Disconnect from session so that the updates on updatedFamily are not directly saved in db
        em.detach(updatedFamily);
        updatedFamily
            .hofEjamaatId(UPDATED_HOF_EJAMAAT_ID)
            .hofFullName(UPDATED_HOF_FULL_NAME)
            .emailAddress(UPDATED_EMAIL_ADDRESS)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .totalGents(UPDATED_TOTAL_GENTS)
            .totalLadies(UPDATED_TOTAL_LADIES)
            .totalKids(UPDATED_TOTAL_KIDS)
            .totalPregnants(UPDATED_TOTAL_PREGNANTS)
            .totalDecesead(UPDATED_TOTAL_DECESEAD)
            .zakatulFitr(UPDATED_ZAKATUL_FITR)
            .najwatusukr(UPDATED_NAJWATUSUKR)
            .khumus(UPDATED_KHUMUS)
            .silatulImam(UPDATED_SILATUL_IMAM)
            .nazrulMaqam(UPDATED_NAZRUL_MAQAM)
            .lastUpdateTstamp(UPDATED_LAST_UPDATE_TSTAMP)
            .submittedBy(UPDATED_SUBMITTED_BY);

        restFamilyMockMvc.perform(put("/api/families")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFamily)))
            .andExpect(status().isOk());

        // Validate the Family in the database
        List<Family> familyList = familyRepository.findAll();
        assertThat(familyList).hasSize(databaseSizeBeforeUpdate);
        Family testFamily = familyList.get(familyList.size() - 1);
        assertThat(testFamily.getHofEjamaatId()).isEqualTo(UPDATED_HOF_EJAMAAT_ID);
        assertThat(testFamily.getHofFullName()).isEqualTo(UPDATED_HOF_FULL_NAME);
        assertThat(testFamily.getEmailAddress()).isEqualTo(UPDATED_EMAIL_ADDRESS);
        assertThat(testFamily.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testFamily.getTotalGents()).isEqualTo(UPDATED_TOTAL_GENTS);
        assertThat(testFamily.getTotalLadies()).isEqualTo(UPDATED_TOTAL_LADIES);
        assertThat(testFamily.getTotalKids()).isEqualTo(UPDATED_TOTAL_KIDS);
        assertThat(testFamily.getTotalPregnants()).isEqualTo(UPDATED_TOTAL_PREGNANTS);
        assertThat(testFamily.getTotalDecesead()).isEqualTo(UPDATED_TOTAL_DECESEAD);
        assertThat(testFamily.getZakatulFitr()).isEqualTo(UPDATED_ZAKATUL_FITR);
        assertThat(testFamily.getNajwatusukr()).isEqualTo(UPDATED_NAJWATUSUKR);
        assertThat(testFamily.getKhumus()).isEqualTo(UPDATED_KHUMUS);
        assertThat(testFamily.getSilatulImam()).isEqualTo(UPDATED_SILATUL_IMAM);
        assertThat(testFamily.getNazrulMaqam()).isEqualTo(UPDATED_NAZRUL_MAQAM);
        assertThat(testFamily.getLastUpdateTstamp()).isEqualTo(UPDATED_LAST_UPDATE_TSTAMP);
        assertThat(testFamily.getSubmittedBy()).isEqualTo(UPDATED_SUBMITTED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingFamily() throws Exception {
        int databaseSizeBeforeUpdate = familyRepository.findAll().size();

        // Create the Family

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFamilyMockMvc.perform(put("/api/families")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(family)))
            .andExpect(status().isBadRequest());

        // Validate the Family in the database
        List<Family> familyList = familyRepository.findAll();
        assertThat(familyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFamily() throws Exception {
        // Initialize the database
        familyRepository.saveAndFlush(family);

        int databaseSizeBeforeDelete = familyRepository.findAll().size();

        // Delete the family
        restFamilyMockMvc.perform(delete("/api/families/{id}", family.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Family> familyList = familyRepository.findAll();
        assertThat(familyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
