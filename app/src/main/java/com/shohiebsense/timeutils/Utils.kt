package com.shohiebsense.timeutils

import java.lang.StringBuilder
import java.sql.Timestamp
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object Utils {


    object Time {
        const val DEFAULLT_DATE_FORMAT = "yyyy-MM-dd"
        const val DEFAULT_TIMESTAMP_FORMAT =  "yyyy-MM-dd HH:mm:ss"

        fun getCalendar() : Calendar{
            return Calendar.getInstance()
        }

        fun getDateCurrent() : String{
            val dateFormat= SimpleDateFormat(DEFAULLT_DATE_FORMAT)
            return dateFormat.format(Date())
        }

        fun getMonthCurrent() : String {
            val today = getDateCurrent()
            return today.split("-")[1]
        }

        fun getYearCurrent() : String {
            val today = getDateCurrent()
            return today.split("-")[0]
        }

        fun getTimeStampCurrent() : String{
            val date = Date()
            val timeStamp = Timestamp(date.time)
            val dateFormat = SimpleDateFormat(DEFAULT_TIMESTAMP_FORMAT)
            return dateFormat.format(date)
        }

        fun isToday(timeStamp : String) : Boolean {
            val dateFormat = SimpleDateFormat(DEFAULLT_DATE_FORMAT)
            val date = dateFormat.format(timeStamp)
            return (date.equals(getDateCurrent()))
        }

        fun changeTimeStampFormat(timeStamp : String) : String{
            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
            val defaultDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            try{
                val timeStampDate = defaultDateFormat.parse(timeStamp)
                return dateFormat.format(timeStampDate)
            }catch (e : ParseException){
                //log
                return timeStamp
            }
        }

        fun getDateFromTime(timeStamp: String): String {
            return timeStamp.substring(0,10)
        }

        fun getHourAndMinutesFromTimeStamp(timeStamp : String) : String{
            if(timeStamp.isEmpty()) return ""
            val dateTimeSplit = timeStamp.split(" ")
            if(dateTimeSplit.size > 1){
                val timeSplit = dateTimeSplit[0]
                if(timeSplit.length > 4){
                    return timeSplit.substring(0,5)
                }
            }
            return ""
        }

        fun getCurrentHourMinuteSecond(): String{
            val date = getDateCurrent()
            var currentHourMinuteSecond = ""
            if(date.contains(" ")){
                currentHourMinuteSecond = date.split(" ")[1]
            }
            return currentHourMinuteSecond
        }

        //0 -> 00
        //1 =? 01
        fun getStandardDigitsInTime(hoursOrMinutes : Long) : String{
            var strHourMinutes = hoursOrMinutes.toString()
            if(strHourMinutes.length == 1){
                val strBuilder = StringBuilder()
                strBuilder.append("0")
                strBuilder.append(strHourMinutes)
                strHourMinutes = strBuilder.toString()
            }
            return strHourMinutes
        }

        fun getStandardDigitsInTime(hoursOrMinutes : Int) : String{
            return getStandardDigitsInTime(hoursOrMinutes.toLong())
        }


        fun getTimeDifference(firstTimeStr : String, secondTimeStr : String) : String{
            val simpleDateFormat = SimpleDateFormat(DEFAULT_TIMESTAMP_FORMAT)
            try{
                val firstDate = simpleDateFormat.parse(firstTimeStr)
                val secondDate = simpleDateFormat.parse(secondTimeStr)
                val difference = secondDate.time - firstDate.time

                if(difference < 0){
                    return ""
                }
                val diffSeconds = difference / 1000 % 60
                val diffMinutes = difference / (60 * 1000) % 60
                val diffHours = difference / (60 * 60 * 1000) % 24
                val diffDays = difference / (24*60*60*1000)

                val diffHoursStr = getStandardDigitsInTime(diffHours)
                val diffMinutesStr = getStandardDigitsInTime(diffMinutes)
                val differenceStr = "$diffHoursStr:$diffMinutesStr"
                return differenceStr
            }
            catch (e : Exception){
                //log...
                return ""
            }
        }



    }
}