import java.util.*;

public class PortfolioEliminator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = Integer.parseInt(scanner.nextLine());

        String[] assetNames = scanner.nextLine().split(" ");

        int[] currentAllocations = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        int[] targetAllocations = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        Map<String, Integer> currentMap = new HashMap<>();
        Map<String, Integer> targetMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            currentMap.put(assetNames[i], currentAllocations[i]);
            targetMap.put(assetNames[i], targetAllocations[i]);
        }

        IPortfolio currentPortfolio = new SimplePortfolio(currentMap);
        IPortfolio targetPortfolio = new SimplePortfolio(targetMap);

        Map<String, Integer> adjustments = PortfolioManager.rebalancePortfolio(currentPortfolio, targetPortfolio);

        for (String asset : assetNames) {
            System.out.println(asset + ": " + adjustments.get(asset) + "%");
        }

        scanner.close();
    }

    interface IPortfolio {
        Map<String, Integer> getAllocations();
    }

    static class PortfolioManager {
        public static Map<String, Integer> rebalancePortfolio(IPortfolio currentPortfolio, IPortfolio targetPortfolio) {
            Map<String, Integer> currentMap = currentPortfolio.getAllocations();
            Map<String, Integer> targetMap = targetPortfolio.getAllocations();

            Set<String> allAssets = new HashSet<>();
            allAssets.addAll(currentMap.keySet());
            allAssets.addAll(targetMap.keySet());

            Map<String, Integer> adjustments = new HashMap<>();

            for (String asset : allAssets) {
                int currentAllocation = currentMap.getOrDefault(asset, 0);
                int targetAllocation = targetMap.getOrDefault(asset, 0);
                adjustments.put(asset, targetAllocation - currentAllocation);
            }

            return adjustments;
        }
    }

    static class SimplePortfolio implements IPortfolio {
        private final Map<String, Integer> allocations;

        public SimplePortfolio(Map<String, Integer> allocations) {
            this.allocations = new HashMap<>(allocations);
        }

        @Override
        public Map<String, Integer> getAllocations() {
            return allocations;
        }
    }
}
/*
Bir portföy dengeleme sistemi yaz.

        Senaryo: Elimizde varlıklar var (hisse, tahvil, nakit vs.) ve her birinin mevcut yüzdesi ile hedef yüzdesi var. Toplam her zaman %100.

Yapılacak tek şey: Her varlık için hedef - mevcut = fark hesapla.

Fark pozitifse → al
Fark negatifse → sat
Fark sıfırsa → dokunma
Örnek:

Hisse: mevcut %60, hedef %50 → -10% (sat)
Tahvil: mevcut %30, hedef %40 → +10% (al)
Nakit: mevcut %10, hedef %10 → 0% (değişiklik yok)
Kod tarafında:

IPortfolio → interface (verilmiş, dokunma)
SimplePortfolio → Map<String, Integer> tutar, constructor ile alır
PortfolioManager.rebalancePortfolio() → iki portföyü alır, farkları hesaplayıp döner
Kısacası basit bir çıkarma işlemi, sadece class yapısını doğru kurmak gerekiyor.*/
