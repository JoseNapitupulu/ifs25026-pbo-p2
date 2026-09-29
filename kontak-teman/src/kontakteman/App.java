package kontakteman;

import adapter.presenter.ContactPresenter;
import adapter.repository.ContactRepository;
import framework.view.ContactView;
import usecase.ContactUseCase;

/** Entry point aplikasi Kontak Teman. */
public class App {
    public static void main(String[] args) {
        ContactRepository repository = new ContactRepository();
        ContactUseCase useCase = new ContactUseCase(repository);
        ContactPresenter presenter = new ContactPresenter();
        new ContactView(useCase, presenter).show();
    }
}
