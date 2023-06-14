package com.example.myappforbtu1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    {
        data class Item(val name: String, val description: String)

    }

    import com.firebase.ui.database.FirebaseRecyclerOptions
    import com.google.firebase.database.DatabaseReference
    import com.google.firebase.database.FirebaseDatabase

    private lateinit var databaseReference: DatabaseReference

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FirebaseRecyclerAdapter<Item, ItemViewHolder>

    databaseReference = FirebaseDatabase.getInstance().reference.child("items")
    val options = FirebaseRecyclerOptions.Builder<Item>()
        .setQuery(databaseReference, Item::class.java)
        .build()

    adapter = object : FirebaseRecyclerAdapter<Item, ItemViewHolder>(options) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
            return ItemViewHolder(view)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int, model: Item) {
            holder.bind(model)
        }
    }

    recyclerView = findViewById(R.id.recyclerView)
    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter = adapter

    adapter.startListening()

}

{
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item) {
            itemView.findViewById<TextView>(R.id.nameTextView).text = item.name
            itemView.findViewById<TextView>(R.id.descriptionTextView).text = item.description

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, SecondActivity::class.java)
                intent.putExtra("name", item.name)
                intent.putExtra("description", item.description)
                itemView.context.startActivity(intent)
            }
            override fun onStop() {
                super.onStop()
                adapter.stopListening()
            }

        }
    }

}