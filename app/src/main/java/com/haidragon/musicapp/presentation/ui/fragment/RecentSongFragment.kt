package com.haidragon.musicapp.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.haidragon.musicapp.R
import com.haidragon.musicapp.databinding.FragmentRecentSongFragmetBinding
import com.haidragon.musicapp.presentation.base.BaseFragment
import com.remotetechs.musicapp.presentation.ui.adapter.SongInPlayListAdapter
import com.haidragon.musicapp.presentation.utils.Constant
import com.haidragon.musicapp.presentation.utils.OnBlackListener
import com.haidragon.musicapp.presentation.utils.SharedPref
import com.haidragon.musicapp.presentation.utils.SingletonApp
import com.haidragon.musicapp.presentation.viewmodel.SongViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecentSongFragment : BaseFragment<FragmentRecentSongFragmetBinding>() {
    private lateinit var activity: FragmentActivity
    private lateinit var onBlackListener: OnBlackListener
    private lateinit var recyclerSongRecent: RecyclerView
    private lateinit var sharedPref: SharedPref
    private lateinit var namePlayList: String
    private lateinit var songInPlayListAdapter:SongInPlayListAdapter
    private val songViewModel: SongViewModel by viewModels()
    @Inject
    lateinit var singletonApp: SingletonApp

    companion object {
        fun newInstance(namePlayList:String?): RecentSongFragment {
            val fragment = RecentSongFragment()
            val args = Bundle()
            args.putString(Constant.SEND_SONG_RECENTLY, namePlayList)
            fragment.arguments = args
            return fragment
        }
    }
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentRecentSongFragmetBinding =
        FragmentRecentSongFragmetBinding.inflate(layoutInflater, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sharedPref= SharedPref()
        if (context is FragmentActivity) {
            activity = context
        }
        if (context is OnBlackListener) {
            onBlackListener = context
        }
    }

    override fun initUI() {
        super.initUI()
        recyclerSongRecent=binding.recyclerSongRecent
    }

    override fun setUpView() {
        super.setUpView()
        val myView = binding.root
        songInPlayListAdapter=SongInPlayListAdapter()
        recyclerSongRecent.adapter=songInPlayListAdapter
        namePlayList = arguments?.getString(Constant.SEND_SONG_RECENTLY)?:"Name Null"
        Log.d("aaaaaaaaaaaadd", "setUpView: $namePlayList")
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.animation_right_left)
        myView.startAnimation(animation)
        //songRecentAdapter= SongRecentAdapter()
        //setUpRecycler()
        onBack()
    }

    private fun setUpRecycler() {

    }

    override fun observeViewModel() {
        super.observeViewModel()
        songViewModel.liveDataPlaylist.observe(viewLifecycleOwner){playlist->
            songInPlayListAdapter.submitList(playlist.listSongInPlaylist)
        }
    }

    override fun setupViewModel() {
        super.setupViewModel()
       // songViewModel.readPlaylistWithFilesByName(namePlayList)
        songViewModel.realPlaylistByName(namePlayList)

    }

    private fun onBack() {
        activity.onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBlackListener.OnBlack(0)
                    removeCurrentFragment()
                    val view = binding.root
                    view.setBackgroundResource(R.drawable.bck_bottom_play_music)
                    val slideRightAnimation = TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 1f,
                        Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 0f
                    )
                    slideRightAnimation.duration = 500
                    slideRightAnimation.fillAfter = true
                    view.startAnimation(slideRightAnimation)
                }
            })
    }
    private fun removeCurrentFragment() {
        val fragmentManager = activity.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.remove(this)
        fragmentTransaction.commit()
    }
}