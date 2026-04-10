package com.example.luby_katalog.model

data class Product(
    val id: Int,
    val name: String,
    val price: String,
    val originalPrice: String? = null,
    val badge: String? = null,
    val description: String = "",
    val descTitle: String = "",
    val series: String = "",
    val batteryLife: String = "",
    val lumenOutput: String = "",
    val material: String = "",
    val chargingTime: String = "",
    val chargingType: String = "",
    val waterResistance: String = "",
    val beamDistance: String = ""
) {
    companion object {
        fun getSampleProducts(): List<Product> = listOf(
            Product(
                id = 1,
                name = "Luby Flashlight L-892",
                price = "Rp 129.000",
                originalPrice = "Rp 189.000",
                badge = "BEST",
                description = "The Luby L-892 isn't just a tool; it's a beacon of reliability. Engineered for extreme environments, it combines a high-intensity long-range beam with advanced battery management. Whether you're navigating a blackout or exploring the great outdoors, the L-892 provides surgical precision in the dark.",
                descTitle = "Illuminating the Unknown",
                series = "THE PRECISION SERIES",
                batteryLife = "24 Hours",
                lumenOutput = "1200 LM",
                material = "Aero-grade Aluminum",
                chargingTime = "3.5 Hours",
                chargingType = "USB-C Fast Charge",
                waterResistance = "IPX-6 Rated",
                beamDistance = "500 Meters"
            ),
            Product(
                id = 2,
                name = "Desk Pro Series L-102",
                price = "Rp 345.000",
                description = "Professional desk lamp with adjustable color temperature and brightness. Perfect for long working hours with eye-care technology.",
                descTitle = "Professional Illumination",
                series = "THE PRO SERIES",
                batteryLife = "12 Hours",
                lumenOutput = "800 LM",
                material = "Premium ABS Plastic",
                chargingTime = "2 Hours",
                chargingType = "USB-C",
                waterResistance = "IPX-2 Rated",
                beamDistance = "5 Meters"
            ),
            Product(
                id = 3,
                name = "Smart Aura Bulb V3",
                price = "Rp 89.000",
                description = "Smart LED bulb with adjustable color temperature and remote control capability. Energy efficient with long lifespan.",
                descTitle = "Smart Living",
                series = "THE SMART SERIES",
                batteryLife = "25,000 Hours",
                lumenOutput = "1000 LM",
                material = "Polycarbonate",
                chargingTime = "-",
                chargingType = "E27 Socket",
                waterResistance = "IPX-0",
                beamDistance = "360°"
            ),
            Product(
                id = 4,
                name = "Solar Sentinel L-5000",
                price = "Rp 1.250.000",
                description = "High-power solar floodlight designed for outdoor security and landscape lighting. Features advanced solar charging and motion detection.",
                descTitle = "Solar Powered Security",
                series = "THE SENTINEL SERIES",
                batteryLife = "10 Hours",
                lumenOutput = "5000 LM",
                material = "Die-cast Aluminum",
                chargingTime = "6 Hours (Solar)",
                chargingType = "Solar Panel",
                waterResistance = "IPX-5 Rated",
                beamDistance = "30 Meters"
            )
        )
    }
}
