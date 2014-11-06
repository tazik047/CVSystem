package ua.nure.pi.test;

import ua.nure.pi.security.Hashing;

public class createPass {

	public static void main(String[] args) {
		System.out.println(Hashing.salt("pass", "login"));
	}

}
