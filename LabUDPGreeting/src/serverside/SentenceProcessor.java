package serverside;

public class SentenceProcessor {

	private int size = 65535;

	private String sentence;

	public SentenceProcessor(byte[] byteData) {

		this.sentence = new String(byteData);
	}

	public String getSentence() {

		return sentence;
	}

	/**
	 * This method convert value into an array of byte
	 * 
	 * @param length
	 * @return
	 */
	public byte[] convertToByteArray(int value) {

		byte[] outData = new byte[size];

		String stringValue = Integer.valueOf(value).toString();

		outData = stringValue.getBytes();

		return outData;
	}

	/**
	 * This method count the number of characters in a sentence
	 * 
	 * @return
	 */
	public int countCharacters() {

		int index = 0;
		char currentChar = sentence.charAt(index);
		while (currentChar != '\0') {

			currentChar = sentence.charAt(index++);
		}

		return index - 1;
	}

	public int countVowel() {
		int index = 0;
		char[] vowel = {'a','e','i','o','u'};
		char currentChar = sentence.charAt(index);
		int count =0;
		//loop through the sentence
		while (currentChar != '\0') {
			
			currentChar = Character.toLowerCase(sentence.charAt(index++));
			//count the vowel
			for(char v:vowel) {
				if(v==currentChar) {
					count++;
				}
				
			}
		}
		
		return count;

	}

	
	public int countConsonant() {
		int index = 0;
		// B, C, D, F, G, J, K, L, M, N, P, Q, S, T, V, X, Z
		char[] consonants = {'b','c','d','f','g','j','k','l','m','n','p','q','s','t','v','x','z','h','r','w','y'};
		char currentChar = sentence.charAt(index);
		int count =0;
		//loop through the sentence
		while (currentChar != '\0') {

			currentChar = Character.toLowerCase(sentence.charAt(index++));
			//count the consonant
			for(char c:consonants) {
				if(c==currentChar) {
					count++;
				}
				
			}
		}
		
		return count;

	}
	public int countPunctuation() {
		int index = 0;
		
		char[] punctuations = {',','.','!','?'};
		char currentChar = sentence.charAt(index);
		int count =0;
		//loop through the sentence
		while (currentChar != '\0') {

			currentChar = Character.toLowerCase(sentence.charAt(index++));
			//count the consonant
			for(char p:punctuations) {
				if(p==currentChar) {
					count++;
				}
				
			}
		}
		
		return count;

	}
}
