package jeucarte;

public enum Typeinverse {
	
	// ne sert à rien pour le moment, j'au fait un switch car je ne sais pas comme appeler l'enum avec Typeinverse.xxx.value
	// ou xxx serai un string de valeur V01 ou VAS,...
	
	V01(1),V02(2),V03(3),V04(4),V05(5),V06(6),V07(7),V08(8),V09(9),V10(10),VVA(11),
	VDA(13),VRO(14),VAS(15),VCA(12),V11(11),V12(12),V13(13),
	V14(14),V15(15),V16(16),V17(17),V18(18),V19(19),V20(20),V21(21),VVV(22);
	
    private final int value;

    private Typeinverse(int value) {
        this.value = value;
    }
	
	
}
