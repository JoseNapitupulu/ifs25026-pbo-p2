package jadwalkegiatan;

import adapter.presenter.ActivityPresenter;
import adapter.repository.ActivityRepository;
import framework.view.ActivityView;
import usecase.ActivityUseCase;

/** Entry point aplikasi Jadwal Kegiatan. */
public class App {
    public static void main(String[] args) {
        ActivityRepository repository = new ActivityRepository();
        ActivityUseCase useCase = new ActivityUseCase(repository);
        ActivityPresenter presenter = new ActivityPresenter();
        new ActivityView(useCase, presenter).show();
    }
}
