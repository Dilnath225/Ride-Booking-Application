import { CheckCircle, MapPin, Navigation, Car, Calendar, User } from 'lucide-react';

const BookingConfirmation = ({ booking, onReset }) => {
    if (!booking) return null;

    // Formatting date
    const formattedDate = booking.bookingTime
        ? new Date(booking.bookingTime).toLocaleString()
        : new Date().toLocaleString();

    return (
        <div className="glass-panel confirmation-card">
            <div className="success-icon-wrapper">
                <CheckCircle size={32} />
            </div>

            <h2 style={{ marginBottom: '0.5rem', color: 'var(--text-main)' }}>Booking Confirmed!</h2>
            <p style={{ color: 'var(--text-muted)', marginBottom: '2rem' }}>
                Your ride has been successfully booked and a driver is on the way.
            </p>

            <div style={{ background: 'rgba(15, 23, 42, 0.4)', borderRadius: '12px', padding: '1.5rem', textAlign: 'left', marginBottom: '2rem' }}>
                <h3 style={{ fontSize: '1.1rem', marginBottom: '1rem', display: 'flex', alignItems: 'center', gap: '0.5rem', borderBottom: '1px solid var(--glass-border)', paddingBottom: '0.5rem' }}>
                    <Car size={18} color="var(--primary)" />
                    Driver Details
                </h3>

                <div className="detail-row">
                    <span className="detail-label">Driver Name</span>
                    <span className="detail-value" style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
                        <User size={14} color="var(--text-muted)" /> {booking.driverName}
                    </span>
                </div>
                <div className="detail-row">
                    <span className="detail-label">Vehicle</span>
                    <span className="detail-value">{booking.vehicleModel}</span>
                </div>
                <div className="detail-row">
                    <span className="detail-label">Plate Number</span>
                    <span className="detail-value" style={{ background: 'rgba(255,255,255,0.1)', padding: '2px 6px', borderRadius: '4px', fontFamily: 'monospace' }}>
                        {booking.vehicleNumber}
                    </span>
                </div>

                <h3 style={{ fontSize: '1.1rem', marginBottom: '1rem', marginTop: '1.5rem', display: 'flex', alignItems: 'center', gap: '0.5rem', borderBottom: '1px solid var(--glass-border)', paddingBottom: '0.5rem' }}>
                    <Navigation size={18} color="var(--accent)" />
                    Trip Details
                </h3>

                <div className="detail-row">
                    <span className="detail-label">From</span>
                    <span className="detail-value" style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
                        <MapPin size={14} color="var(--danger)" /> {booking.pickupLocation}
                    </span>
                </div>
                <div className="detail-row">
                    <span className="detail-label">To</span>
                    <span className="detail-value" style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
                        <Navigation size={14} color="var(--primary)" /> {booking.dropoffLocation}
                    </span>
                </div>
                <div className="detail-row">
                    <span className="detail-label">Time</span>
                    <span className="detail-value" style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
                        <Calendar size={14} color="var(--text-muted)" /> {formattedDate}
                    </span>
                </div>
            </div>

            <button onClick={onReset} className="btn" style={{ background: 'transparent', border: '1px solid var(--glass-border)' }}>
                Book Another Ride
            </button>
        </div>
    );
};

export default BookingConfirmation;
