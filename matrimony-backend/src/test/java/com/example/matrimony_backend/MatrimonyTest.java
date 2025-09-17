package com.example.matrimony_backend;

import com.example.MatrimonialBackendApplication;
import com.example.controller.CasteController;
import com.example.controller.GotraController;
import com.example.controller.NakshatraController;
import com.example.controller.RashiController;
import com.example.controller.ReligionController;
import com.example.entity.Caste;
import com.example.entity.Gotra;
import com.example.entity.Nakshatra;
import com.example.entity.Rashi;
import com.example.entity.Religion;
import com.example.repository.CasteRepository;
import com.example.repository.GotraRepository;
import com.example.repository.NakshatraRepository;
import com.example.repository.RashiRepository;
import com.example.repository.ReligionRepository;
import com.example.service.CasteService;
import com.example.service.ReligionService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MatrimonyTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CasteRepository casteRepository;

    @Autowired
    private GotraRepository gotraRepository;

    @Autowired
    private NakshatraRepository nakshatraRepository;

    @Autowired
    private RashiRepository rashiRepository;

    @Autowired
    private ReligionRepository religionRepository;

    @Autowired
    private CasteController casteController;

    @Autowired
    private GotraController gotraController;

    @Autowired
    private NakshatraController nakshatraController;

    @Autowired
    private RashiController rashiController;

    @Autowired
    private ReligionController religionController;

    @Autowired
    private CasteService casteService;

    @Autowired
    private ReligionService religionService;

    @Test
    void mainMethodRuns() {
        MatrimonialBackendApplication.main(new String[]{});
    }
    // add a new caste
    @Test
    @DisplayName("Test: Add a new Caste")
    public void testCreateCaste() throws Exception {
        // Create and persist a Religion first
        Religion religion = new Religion();
        religion.setReligionName("Hindu");
        religion = religionRepository.save(religion); // gets a real ID from DB

        // Use the generated ID in the request JSON
        String casteJson = "{ \"casteName\": \"Brahmin\", " +
                "\"religion\": { \"religionId\": " + religion.getReligionId() + " } }";

        // Perform the POST request
        mockMvc.perform(post("/api/castes")
                        .contentType("application/json")
                        .content(casteJson))
                .andExpect(status().isCreated());
    }



    // Test: Get all Castes
    @Test
    @DisplayName("Test: Get all Castes")
    public void testGetAllCastes() throws Exception {
        mockMvc.perform(get("/api/castes"))
                .andExpect(status().isOk());
    }







    // Test: Get Castes by Religion ID
    @Test
    @DisplayName("Test: Get Castes by Religion ID")
    public void testGetCastesByReligion() throws Exception {
        mockMvc.perform(get("/api/castes/by-religion/1"))
                .andExpect(status().isOk());
    }

    // Test: Add a new Religion
    @Test
    @DisplayName("Test: Add a new Religion")
    public void testAddReligion() throws Exception {
        mockMvc.perform(post("/api/religions")
                        .contentType("application/json")
                        .content("{\"religionName\": \"Hindu\"}"))
                .andExpect(status().isCreated());
    }



    // Test: Get all Religions
    @Test
    @DisplayName("Test: Get all Religions")
    public void testGetAllReligions() throws Exception {
        mockMvc.perform(get("/api/religions"))
                .andExpect(status().isOk());
    }

    // Test: Get Gotras
    @Test
    @DisplayName("Test: Get Gotras")
    public void testGetAllGotras() throws Exception {
        mockMvc.perform(get("/api/gotras"))
                .andExpect(status().isOk());
    }

    // Test: Get Gotra by ID
    @Test
    @DisplayName("Test: Get Gotra by ID")
    public void testGetGotraById() throws Exception {
        // Fetch a Religion entity from the database
        Religion religion = religionRepository.findById(6)
                .orElseGet(() -> {
                    Religion newReligion = new Religion();
                    newReligion.setReligionName("Hindu");
                    return religionRepository.save(newReligion); // Save it if it doesn't exist
                });

        // Create and save the Gotra entity
        Gotra gotra = new Gotra();
        gotra.setGotraName("Kashyap");
        gotraRepository.save(gotra);

        // Perform the GET request to retrieve the Gotra by ID
        mockMvc.perform(get("/api/gotras/" + gotra.getGotraId()))
                .andExpect(status().isOk());
    }




    // Test: Get all Nakshatras
    @Test
    @DisplayName("Test: Get all Nakshatras")
    public void testGetAllNakshatras() throws Exception {
        mockMvc.perform(get("/api/nakshatras"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test: Get Nakshatra by ID")
    public void testGetNakshatraById() throws Exception {
        // Create a Nakshatra object and save it to the repository
        Nakshatra nakshatra = new Nakshatra();
        nakshatra.setNakshatraName("Ashwini"); // Set the name or other properties
        nakshatraRepository.save(nakshatra);

        // Get the generated ID of the saved Nakshatra
        int nakshatraId = nakshatra.getNakshatraId(); // Use the ID generated by the database

        // Perform the GET request to retrieve the Nakshatra by its ID
        mockMvc.perform(get("/api/nakshatras/" + nakshatraId))
                .andExpect(status().isOk());
    }


    // Test: Get all Rashis
    @Test
    @DisplayName("Test: Get all Rashis")
    public void testGetAllRashis() throws Exception {
        mockMvc.perform(get("/api/rashis"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test: Get Rashi by ID")
    public void testGetRashiById() throws Exception {
        // Create a Rashi object and save it to the repository
        Rashi rashi = new Rashi();
        rashi.setRashiName("Mesh"); // Set the name or other properties
        rashiRepository.save(rashi);

        // Get the generated ID of the saved Rashi
        int rashiId = rashi.getRashiId(); // Use the ID generated by the database

        // Perform the GET request to retrieve the Rashi by its ID
        mockMvc.perform(get("/api/rashis/" + rashiId))
                .andExpect(status().isOk());
    }


    // Parameterized Test: Valid Religion Add
//    @DisplayName("Parameterized Test: Valid Religion Add")
//    @ParameterizedTest
//    @CsvSource({
//            "1,Hindu,true",
//            "2,Muslim,false"
//    })
//    public void parameterizedTestForReligion(int id, String name, boolean expectedSuccess) {
//        Religion religion = new Religion(id, name);
//        ResponseEntity<Religion> response = religionController.addReligion(religion);
//
//        if (expectedSuccess) {
//            assertEquals(HttpStatus.OK, response.getStatusCode());
//        } else {
//            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        }
//    }

    @Test
    void testCasteGettersSetters() {
        Religion religion = new Religion();
        religion.setReligionId(1);
        religion.setReligionName("Hindu");

        Caste caste = new Caste();
        caste.setCasteId(101);
        caste.setCasteName("Brahmin");
        caste.setReligion(religion);

        // Verify getters
        assertEquals(101, caste.getCasteId());
        assertEquals("Brahmin", caste.getCasteName());
        assertEquals(religion, caste.getReligion());

        // Verify toString
        assertTrue(caste.toString().contains("Brahmin"));

        // Equals & hashCode
        Caste caste2 = new Caste();
        caste2.setCasteId(101);
        caste2.setCasteName("Brahmin");
        caste2.setReligion(religion);

        assertEquals(caste, caste2);
        assertEquals(caste.hashCode(), caste2.hashCode());
    }


    // for gotra
    @Test
    void testGotraGettersSetters() {
        Gotra gotra = new Gotra();
        gotra.setGotraId(1);
        gotra.setGotraName("Bharadwaj");

        assertEquals(1, gotra.getGotraId());
        assertEquals("Bharadwaj", gotra.getGotraName());
        assertTrue(gotra.toString().contains("Bharadwaj"));

        Gotra gotra2 = new Gotra();
        gotra2.setGotraId(1);
        gotra2.setGotraName("Bharadwaj");

        assertEquals(gotra, gotra2);
        assertEquals(gotra.hashCode(), gotra2.hashCode());
    }



    // for nakshatras


    @Test
    void testNakshatraGettersSetters() {
        Nakshatra nakshatra = new Nakshatra();
        nakshatra.setNakshatraId(10);
        nakshatra.setNakshatraName("Rohini");

        assertEquals(10, nakshatra.getNakshatraId());
        assertEquals("Rohini", nakshatra.getNakshatraName());
        assertTrue(nakshatra.toString().contains("Rohini"));

        Nakshatra nakshatra2 = new Nakshatra();
        nakshatra2.setNakshatraId(10);
        nakshatra2.setNakshatraName("Rohini");

        assertEquals(nakshatra, nakshatra2);
        assertEquals(nakshatra.hashCode(), nakshatra2.hashCode());
    }



    // for rashis
    @Test
    void testRashiGettersSetters() {
        Rashi rashi = new Rashi();
        rashi.setRashiId(5);
        rashi.setRashiName("Mesha");

        assertEquals(5, rashi.getRashiId());
        assertEquals("Mesha", rashi.getRashiName());
        assertTrue(rashi.toString().contains("Mesha"));

        Rashi rashi2 = new Rashi();
        rashi2.setRashiId(5);
        rashi2.setRashiName("Mesha");

        assertEquals(rashi, rashi2);
        assertEquals(rashi.hashCode(), rashi2.hashCode());
    }


    @Test
    void testReligionGettersSetters() {
        Religion religion = new Religion();
        religion.setReligionId(1);
        religion.setReligionName("Hindu");

        assertEquals(1, religion.getReligionId());
        assertEquals("Hindu", religion.getReligionName());
        assertTrue(religion.toString().contains("Hindu"));

        Religion religion2 = new Religion();
        religion2.setReligionId(1);
        religion2.setReligionName("Hindu");

        assertEquals(religion, religion2);
        assertEquals(religion.hashCode(), religion2.hashCode());
    }





    @BeforeEach
    public void setup() {
        casteRepository.deleteAll();
        gotraRepository.deleteAll();
        nakshatraRepository.deleteAll();
        rashiRepository.deleteAll();
        religionRepository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        // Cleanup logic if necessary
    }

    @AfterAll
    public static void afterAllTests() {
        System.out.println("All tests are complete!");
    }

    @BeforeAll
    public static void beforeAllTests() {
        System.out.println("Running tests for matrimonial backend!");
    }
}
