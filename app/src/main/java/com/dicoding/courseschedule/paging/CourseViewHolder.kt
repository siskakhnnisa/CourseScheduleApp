package com.dicoding.courseschedule.paging

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.data.Course
import com.dicoding.courseschedule.util.DayName.Companion.getByNumber

class CourseViewHolder(view: View): RecyclerView.ViewHolder(view) {

    //all this priv val
    private val tvLecturer: TextView = view.findViewById(R.id.tv_lecturer)
    private val tvTime: TextView = view.findViewById(R.id.tv_time)
    private val tvCourse: TextView = view.findViewById(R.id.tv_course)

    private lateinit var course: Course
    private val timeString = itemView.context.resources.getString(R.string.time_format)

    //TODO 7 : Complete ViewHolder to show item
    fun bind(course: Course, clickListener: (Course) -> Unit) {
        this.course = course

        course.apply {
            val dayName = getByNumber(day)
            val timeFormat = String.format(timeString, dayName, startTime, endTime)
            //this part
            tvLecturer.text = lecturer
            tvTime.text = timeFormat
            tvCourse.text = courseName

        }

        itemView.setOnClickListener {
            clickListener(course)
        }
    }

    fun getCourse(): Course = course
}