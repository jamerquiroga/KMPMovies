import SwiftUI
import Shared

struct DetailScreen: View {
    var viewModel: DetailViewModel
    
    init(movieId: Int32) {
        self.viewModel = DetailViewModel(movieId: movieId)
    }
    
    var body: some View {
        Observing(viewModel.state) { state in
            Text(state.movieDetail.title)
            
        }
    }
}
