import SwiftUI
import Shared

extension Movie: @retroactive Identifiable {}

struct HomeScreen2: View {
    var viewModel = HomeViewModel()
    
    var body: some View {
        Observing(viewModel.state) { state in
            if (state.loading) {
                ProgressView().progressViewStyle(CircularProgressViewStyle())
            } else {
                if (!state.movies.isEmpty) {
                    let columns = [GridItem(.adaptive(minimum: 100))]
                    
                    ScrollView {
                        LazyVGrid(columns: columns) {
                            ForEach(state.movies) { movie in
                                MovieItemView(movie: movie)
                            }
                        }.padding(.horizontal)
                    }
                } else {
                    Text("No hay películas")
                }
            }
        }.onAppear {
            viewModel.onUiReady()
        }
    }
}

struct MovieItemView: View {
    var movie: Movie
    
    var body: some View {
        VStack {
            GeometryReader { geometry in
                ZStack(alignment: .topTrailing) {
                    AsyncImage(url: URL(string: movie.poster)) { phase in
                        switch phase {
                        case .empty:
                            ProgressView()
                                .progressViewStyle(CircularProgressViewStyle())
                                .frame(width: geometry.size.width, height: geometry.size.height)
                        case .success(let image):
                            image
                                .resizable()
                                .aspectRatio(2/3, contentMode: .fill)
                                .frame(width: geometry.size.width, height: geometry.size.height)
                                .clipped()
                                .cornerRadius(8)
                        default:
                            EmptyView()
                        }
                    }
                }
            }.aspectRatio(2/3, contentMode: .fit)
            
            Text(movie.title)
                .font(.caption)
                .lineLimit(1)
                .padding(5)
        }
    }
}