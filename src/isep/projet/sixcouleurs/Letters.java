package isep.projet.sixcouleurs;

public class Letters {

	private final char alphabet[]={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
			'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
			'%','(',')',':',';','è','\'',' ','-','?','!','/','.',',','é','0','1','2','3','4','5','6','7','8','9'};
	
	public int getIndex(char c){
		for (int i=0;i<alphabet.length;i++){
			if (alphabet[i]==c) return i;
		}
		return 61;
	}
	
	public int[] getTabIndex(String s){
		
		int[] ans=new int[s.length()];
		for (int i=0;i<s.length();i++){
			ans[i]=getIndex(s.charAt(i));
		}
		return ans;
	}
	
}
