import adapter.presenter.ContactPresenter;
import adapter.repository.ContactRepository;
import domain.repository.IContactRepository;
import framework.view.ContactView;
import usecase.ContactUseCase;

/**
 * Titik masuk aplikasi (Composition Root).
 * Semua dependency antar layer disusun di sini — satu-satunya tempat
 * yang mengetahui implementasi konkret dari setiap interface.
 */
public class App {
    public static void main(String[] args) {
        // Layer adapter: implementasi konkret repository (penyimpanan in-memory)
        IContactRepository contactRepository = new ContactRepository();

        // Layer usecase: logika bisnis, hanya bergantung pada interface repository
        ContactUseCase contactUseCase = new ContactUseCase(contactRepository);

        // Layer adapter: presenter untuk memformat output ke layar
        ContactPresenter contactPresenter = new ContactPresenter();

        // Layer framework: UI konsol yang menerima input user
        ContactView contactView = new ContactView(contactUseCase, contactPresenter);

        // Menjalankan loop menu utama aplikasi
        contactView.show();
    }
}
