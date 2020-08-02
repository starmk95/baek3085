import java.util.*;

public class Main {
    static char[][] candy;
    static int ans = 1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = sc.nextInt();
        candy = new char[cnt][cnt];
        for (int i=0;i<cnt;i++) {
            candy[i] = sc.next().toCharArray();
        }
        // 브루트 포스
        // n^2개의 데이터를 입력받는데 n^2, 인접한 두 칸 고르기 = 4(n^2) (인전 -> 상하좌우 4가지 경우)
        // 그러나 첫 원소부터 오른쪽 또는 아래의 원소와 바꾸다보면 다음 원소에서의 위 또는 왼쪽 원소와 바꾸는 연산은 중복되는 연산이므로 수행할 필요가 없다.
        // 그러므로 각 원소마다 오른쪽과 아래의 원소만 교환하는 경우만 생각하면 된다. -> 2(n^2)
        // 바꾼 칸의 경우마다 가장 긴 연속 부분을 고르기 (행과 열 모두 탐색) -> 각 경우마다 2(n^2)만큼 걸림
        // 결과적으로 O(n^4) 걸림
        for (int i=0;i<cnt;i++) {
            for (int j=0;j<cnt;j++) {
                // 오른쪽과 교환
                if (j+1 < cnt) {
                    char temp = candy[i][j];
                    candy[i][j] = candy[i][j+1];
                    candy[i][j+1] = temp;
                    int len = findLen();
                    if (ans < len) ans = len;
                    // 원상복귀
                    candy[i][j+1] = candy[i][j];
                    candy[i][j] = temp;
                }
                // 아래쪽과 교환
                if (i+1 < cnt) {
                    char temp = candy[i][j];
                    candy[i][j] = candy[i+1][j];
                    candy[i+1][j] = temp;
                    int len = findLen();
                    if (ans < len) ans = len;
                    // 원상복귀
                    candy[i+1][j] = candy[i][j];
                    candy[i][j] = temp;
                }
            }
        }
        System.out.println(ans);
    }
    static int findLen() {
        int n = candy.length;
        int len = 1;
        for (int i=0;i<n;i++) {
            // 행검사
            int temp = 1;
            for (int j=1;j<n;j++) {
                if (candy[i][j] == candy[i][j-1]) {
                    temp += 1;
                } else temp = 1;
                if (len < temp) len = temp;
            }
            // 열검사
            temp = 1;
            for (int j=1;j<n;j++) {
                if (candy[j][i] == candy[j-1][i]) {
                    temp+=1;
                } else temp = 1;
                if (len < temp) len = temp;
            }
        }
        return len;
    }
}
