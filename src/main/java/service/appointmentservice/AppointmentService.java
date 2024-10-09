/******************************************************************************
 * Module Five Milestone
 * [AppointmentService.java]
 * Author: Michael Lorenz
 * - CS320 - Software Test, Automation QA
 * - Southern New Hampshire University
 *
 * Description:
 * This class supports storing appointments. Allows functionalities to:
 * - add appointments with a unique ID, date, and description
 * - delete appointments per appointment ID
 *
 * Date: Due 10/06/2024
 *****************************************************************************/
package service.appointmentservice;

import java.util.Date;

public class AppointmentService extends BasicService<Appointment> {

    /**
     * Adds an appointment object mapped to its unique id in storage.
     * @param date - Scheduled date of the appointment (Must not be non-null, in the past)
     * @param description - Description of the appointment (Must be non-null, no more than 50 characters)
     * @throws IllegalArgumentException in Appointment object if parameters are invalid format
     */
    public void add(Date date, String description) {
        Appointment appointment = new Appointment(date, description); // create object with specified parameters
        add(appointment); // super method. Adds to entityMap HashMap using unique ID
    }

    // super class contains method for delete appointments per ID

    // UPDATE APPOINTMENT FIELDS
    /**
     * Updates date of appointment with given id to given future date (Date class)
     * @param id Unique identifier of the appointment to update
     * @param date new appointment date (non-null, must be after current system time). Chance of exception if date is
     *             set to actual system time, such as with 'new Date()', use updateDate(String id) instead for setting
     *             to current time.
     * @throws IllegalArgumentException if appointment does not exist or date is invalid.
     */
    /*public void updateName(String id, String name) {
        getEntityById(id).updateField("name", name);
    }

    /**
     * Updates date of appointment with given id to current system time
     * @param id Unique identifier of the appointment to update
     * @throws IllegalArgumentException if appointment does not exist or date is invalid
     */
    /*public void updateDescription(String id, String description) {
        getEntityById(id).updateField("description", description);
    } */


}