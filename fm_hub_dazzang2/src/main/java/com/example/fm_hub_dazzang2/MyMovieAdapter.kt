import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fm_hub_dazzang2.Datas
import com.example.fm_hub_dazzang2.databinding.ItemRecyclerviewBinding

class MyViewHolder(val binding: ItemRecyclerviewBinding):
    RecyclerView.ViewHolder(binding.root)

class MyAdapter(val DataList : MutableList<Datas>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = DataList.size
    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int):
            RecyclerView.ViewHolder =
        MyViewHolder(ItemRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding  = (holder as MyViewHolder).binding
        binding.itemImage.setImageResource(DataList[position].img)
        binding.itemData.text = DataList[position].name
    }


}
