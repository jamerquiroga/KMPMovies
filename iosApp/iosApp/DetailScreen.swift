import SwiftUI
import Shared

struct DetailScreen: View {
    var viewModel: DetailViewModel
    
    init(movieId: Int32) {
        self.viewModel = DetailViewModel(movieId: movieId)
    }
    
    var body: some View {
        Observing(viewModel.state) { state in
            if state.loading {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle())
            } else {
                MovieDetailView(movieDetail: state.movieDetail)
            }
        }
    }
}

struct MovieDetailView: View {
    var movieDetail: MovieDetail
    
    var body: some View {
        ScrollView {
            VStack(alignment: .leading) {
                AsyncImage(url: URL(string: movieDetail.backdropPath)) { phase in
                    switch phase {
                    case .empty:
                        ProgressView()
                            .progressViewStyle(CircularProgressViewStyle())
                    case .success(let image):
                        image
                            .resizable()
                            .aspectRatio(16/9, contentMode: .fill)
                            .frame(maxHeight: 200)
                            .clipped()
                    default:
                        EmptyView()
                    }
                }
                Text(movieDetail.overview)
                    .padding()
                
                VStack(alignment: .leading, spacing: 8) {
                    Text("**Original Language**: \(movieDetail.originalLanguage)")
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Text("**Original ttile**: \(movieDetail.originalTitle)")
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Text("**Release date**: \(movieDetail.releaseDate)")
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Text("**Popularity**: \(movieDetail.popularity)")
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Text("**Vote average**: \(movieDetail.voteAverage)")
                        .frame(maxWidth: .infinity, alignment: .leading)
                }
                .padding()
                .background(Color.secondary.opacity(0.1))
                .frame(maxWidth: .infinity, alignment: .leading)
                .cornerRadius(8)
            }
        }
        .navigationTitle(movieDetail.title)
    }
}
