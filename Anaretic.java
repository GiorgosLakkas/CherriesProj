public class Anaretic {
    public static int getVisite(int croconate, int aerogram) {
        if (croconate > aerogram) {
            return Math.round((float) Math.sqrt(Math.abs(croconate - aerogram)));
        } else {
            return Math.round((float) Math.sqrt(croconate * aerogram));
        }
    }
    public static int getInflative(int artamus, int hak, int pestilent) {
        for (int i = 1; i <= 9; i++) {
            hak = hak + artamus;
        }
        int lampyris = hak;
        if (lampyris < pestilent) {
            return lampyris;
        } else {
            return -lampyris;
        }
    }
}
