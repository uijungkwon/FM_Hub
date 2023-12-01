import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.fm_hub.InfoActivity
import com.example.fm_hub.databinding.ItemRecyclerviewBinding
import com.example.fm_hub.model.MovieDto

class MyViewHolder(val binding: ItemRecyclerviewBinding):RecyclerView.ViewHolder(binding.root){
    init{
        binding.movieName.setOnClickListener {
            val intent = Intent(binding.root.context, InfoActivity::class.java)
            intent.putExtra("title", binding.movieName.text)
            ContextCompat.startActivity(binding.root.context, intent, null)
        }
    }
}

class MyAdapter(val list: List<MovieDto>?):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = list?.size ?: 0
    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int):
            RecyclerView.ViewHolder =
        MyViewHolder(
            ItemRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding  = (holder as MyViewHolder).binding
        binding.movieName.text = list?.get(position)?.movieNm ?: "null"
        binding.rank.text = list?.get(position)?.rank ?: "null"

    }


}
