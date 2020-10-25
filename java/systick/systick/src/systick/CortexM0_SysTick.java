package systick;

public class CortexM0_SysTick implements Cortex_M0_SysTic_Interface {
	private int CVR;
	private int RVR;
	private int CSR;
	private boolean Countflag;
	private boolean Enable;
	private boolean Tickint;
	private boolean Source;
	private boolean Interrupt;//placeholder
	public CortexM0_SysTick() {
		
	}
	public void setCVR(int CVR) { //ustawiamy CVR
		setCountflag(false);
		this.CVR=CVR;
	}
	public void setRVR(int RVR) { //ustawiamy RVR

		this.RVR=RVR;
	}
	public void setCSR(int value) {
		if(getInterrupt()==true);
	}
	public void setInterruptEnable() {
	Tickint=false;
	}
	public void setInterruptDisable() {

	Tickint=false;
	}
	public void setInterrupt(boolean Interrupt) {
		this.Interrupt=Interrupt;
		
	}
	public void setEnable() { //ustawiamy Enable

		Enable=true;
	}
	public void setDisable(){

		Enable=false;
	}
	public void setCountflag(boolean Countflag) { //ustawiamy Countflag
		this.Countflag=Countflag;
	}
	public void setSource(boolean Source) { //ustawiamy CLKSRC
		this.Source=Source;
	}
	public void reset() {
		setDisable();
		setCountflag(false);
		setSource(false);
		setInterruptDisable();
		setEnable();
		
	}
	public boolean getCountflag() {//sprawdzamy Countflaga
		return Countflag;
	}
	public boolean getInterrupt() { //sprawdzamy Tickint
		return Tickint;
	}
	public boolean getEnabled() {// sprawdzamy Enable
		return Enable;
	}
	public boolean checkRVR(int RVR) { //sprawdzamy czy RVR=0
		if(RVR==0) {
			return false;
		}
		else {
			return true;
		}
	
	}	
	public int getCVR() { //sprawdzamy CVR

		return CVR;
	}
	public int getCSR(){
		int CSR=0;
		if(Enable) {
			CSR+=1;
		}
		if(Tickint) {
			CSR+=2;
		}
		if(Source) {
			CSR+=4;
		}
		if(Countflag) {
			CSR+=65536;
		}
		setCountflag(true);
		return CSR;
	}
	public int getRVR() { //sprawdzamy RVR

		return RVR;
	}
	private void tick() { //dekrementacja
		CVR--;
	}
	public void tickInternal() { //korzystamy z zegara wewnêtrznego
		if(Source==true)
			tick();
	}
	public void tickExternal() { //korzystamy z zegara zewnêtrznego

		if(Source==false)
			tick();
	}
	public int isCVR() {
		return CVR;
	}
	public boolean isCountFlag() {
		return Countflag;
	}
	public boolean isEnableFlag() {
		return Enable;
	}
	public boolean isInterruptFlag() {
		return Tickint;
	}
	public boolean isInterrupt() {
		return Interrupt;
	}
	public static void main(String []args ) {
		CortexM0_SysTick timer=new CortexM0_SysTick();
		timer.setEnable(); //w³¹czamy licznik
		timer.setSource(true); //wybieramy zegar
		timer.setInterruptEnable();
		timer.setRVR(100);
		System.out.println("RVR wynosi: " + timer.getRVR()); //check RVR
		timer.setCVR(timer.getRVR()); //ustalamy startow¹ wartoœæ CVR
		while(timer.getEnabled() && timer.checkRVR(timer.getRVR())) //ci¹g³oœæ timera i sprawdzenie warunków odpowiedzialnych za dzia³anie licznika
		{
			timer.setCountflag(false);
			System.out.println("Countflag wynosi: "+ timer.getCountflag());
				for(int i=0;i<timer.getRVR();i++) //zmniejszamy a¿ do RVR
				{
				timer.tickInternal();
				System.out.println(timer.isCVR());
				}
			if(timer.getInterrupt()){		//jeœli ustawiony Tickint
			timer.setInterrupt(true);
			}
			else {
			timer.setCountflag(true);
			}
			System.out.println("Countflag wynosi: "+ timer.getCountflag());
			timer.setCVR(timer.getRVR());
		}	
	}
}
