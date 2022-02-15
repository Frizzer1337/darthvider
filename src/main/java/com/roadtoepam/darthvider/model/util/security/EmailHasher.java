package com.roadtoepam.darthvider.model.util.security;

/**
 * The Class EmailHasher used to hash user id in email.
 */
public class EmailHasher {
	
	/**
	 * Hash id.
	 *
	 * @param id to hash
	 * @return the long
	 */
	public long hashId(long id) {
		
		return id*397+293;
		
	}
	
	/**
	 * Decode id.
	 *
	 * @param hashed by this class id 
	 * @return the decoded id.
	 */
	public long decodeId(long id) {
		
		return (id-293)/397;
		
	}

}
