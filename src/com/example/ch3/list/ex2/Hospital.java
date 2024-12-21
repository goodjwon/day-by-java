package com.example.ch3.list.ex2;

import java.util.LinkedList;
import java.util.List;

public class Hospital {

    private LinkedList<Patient> patientList;

    public Hospital() {
        patientList = new LinkedList<>();
    }

    // 일반 환자 추가 (뒤에 추가)
    public void addPatient(Patient patient) {
        patientList.add(patient);
    }

    // 긴급 환자 추가 (중간에 삽입)
    public void emergencyPatient(Patient patient) {
        //환자명
        patientList.add(findUrgentPosition(),patient);
    }

    // 환자 제거 (앞에서 제거)
    public void removePatient(){

    }

    // 대기열 표시
    public void displayQueue(){

    }

    // 긴급 환자 삽입 위치 결정 (예시로 두 번째 위치에 삽입)
    private int findUrgentPosition(){
        return 1;
    }


    public static void main(String[] args) {
        List<Patient> patient = new LinkedList<>();

        Hospital hospital = new Hospital();

        patient.add(new Patient("홍길동"));
        patient.add(new Patient("이순신"));
        hospital.emergencyPatient(new Patient("긴급환자"));

        hospital.displayQueue();

//        for (String number : patient) {
//            System.out.println("환자: " + number);
//        }
//
//        System.out.println("진료 중: 환자: " + patient.remove(0));
//
//        for (String number : patient) {
//            System.out.println("환자: " + number);
//        }
    }
}

class Patient {
    private String name;

    public Patient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "환자: " + name;
    }
}
