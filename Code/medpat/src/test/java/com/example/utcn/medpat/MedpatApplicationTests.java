package com.example.utcn.medpat;

import com.example.utcn.medpat.communication.dto.AppointmentDTO;
import com.example.utcn.medpat.persistence.model.Appointment;
import com.example.utcn.medpat.persistence.model.Medic;
import com.example.utcn.medpat.persistence.model.Patient;
import com.example.utcn.medpat.persistence.model.User;
import com.example.utcn.medpat.persistence.repository.AppointmentRepository;
import com.example.utcn.medpat.persistence.repository.MedicRepository;
import com.example.utcn.medpat.persistence.repository.PatientRepository;
import com.example.utcn.medpat.persistence.repository.UserRepository;
import com.example.utcn.medpat.service.AppointmentService;
import com.example.utcn.medpat.service.PatientService;
import com.example.utcn.medpat.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MedpatApplicationTests {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private MedicRepository medicRepository;

	@Test
	@Transactional
	public void testLogin() {
		User testUser = new User("testUser", "test", "patient", Long.valueOf("0"));
		userRepository.save(testUser);
		userRepository.flush();

		Optional<User> userOptional = Optional.ofNullable(
		        userService.login(testUser.getUsername(), testUser.getPassword()));
		User user = null;

		if(userOptional.isPresent()) {
			user = userOptional.get();
			System.out.println("type: "+user.getUserType()+", id: "+user.getPersonId());
		}
		assert(user != null);
	}

	@Test
	@Transactional
	public void testMakeAppointment() {
		Patient patient = new Patient("testPatient", "testAddress");
		patientRepository.save(patient);
		patientRepository.flush();

		Medic medic = new Medic("testMedic", "testAddress", "testSpec");
		medicRepository.save(medic);
		medicRepository.flush();

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
		AppointmentDTO appointmentDTO =
                new AppointmentDTO(patient.getId(), medic.getId(), sdf.format(new Date()), medic.getWorkAddress());
		appointmentService.makeAppointment(appointmentDTO);
		assert(appointmentService.getAppointments(true, medic.getId()).size() == 1);

		//verify the date check
        appointmentDTO =
                new AppointmentDTO(patient.getId(), medic.getId(), sdf.format(new Date()), medic.getWorkAddress());
        appointmentService.makeAppointment(appointmentDTO);
        assert(appointmentService.getAppointments(true, medic.getId()).size() == 1);
	}



}
