import adapter.presenter.ActivityPresenter;
import adapter.repository.ActivityRepository;
import domain.repository.IActivityRepository;
import framework.view.ActivityView;
import usecase.ActivityUseCase;

/**
 * Titik masuk aplikasi (Composition Root).
 * Semua dependency antar layer disusun di sini — satu-satunya tempat
 * yang mengetahui implementasi konkret dari setiap interface.
 */
public class App {
    public static void main(String[] args) {
        // Layer adapter: implementasi konkret repository (penyimpanan in-memory)
        IActivityRepository activityRepository = new ActivityRepository();

        // Layer usecase: logika bisnis, hanya bergantung pada interface repository
        ActivityUseCase activityUseCase = new ActivityUseCase(activityRepository);

        // Layer adapter: presenter untuk memformat output ke layar
        ActivityPresenter activityPresenter = new ActivityPresenter();

        // Layer framework: UI konsol yang menerima input user
        ActivityView activityView = new ActivityView(activityUseCase, activityPresenter);

        // Menjalankan loop menu utama aplikasi
        activityView.show();
    }
}
