import { useState } from 'react';
import { MapPin, Navigation, User, CalendarClock } from 'lucide-react';
import { bookingService } from '../services/api';

const BookingForm = ({ onBookingSuccess }) => {
    const [formData, setFormData] = useState({
        passengerName: '',
        pickupLocation: '',
        dropoffLocation: ''
    });
    const [isSubmitting, setIsSubmitting] = useState(false);
    const [error, setError] = useState(null);

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setIsSubmitting(true);
        setError(null);

        try {
            const response = await bookingService.createBooking(formData);
            onBookingSuccess(response);
        } catch (err) {
            console.error("Booking failed:", err);
            setError(err.response?.data?.message || "Failed to create booking. Are there any available drivers?");
            setIsSubmitting(false);
        }
    };

    return (
        <div className="glass-panel">
            <h2 style={{ marginBottom: '1.5rem', display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
                <CalendarClock size={24} color="var(--accent)" />
                Book a Ride
            </h2>
            
            {error && (
                <div style={{ padding: '1rem', background: 'rgba(239, 68, 68, 0.1)', color: 'var(--danger)', borderRadius: '8px', marginBottom: '1.5rem', fontSize: '0.9rem' }}>
                    {error}
                </div>
            )}

            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Passenger Name</label>
                    <div style={{ position: 'relative' }}>
                        <User size={18} style={{ position: 'absolute', top: '14px', left: '12px', color: 'var(--text-muted)' }} />
                        <input 
                            type="text" 
                            name="passengerName"
                            className="form-input" 
                            style={{ paddingLeft: '2.5rem' }}
                            placeholder="Enter your name" 
                            value={formData.passengerName}
                            onChange={handleChange}
                            required 
                        />
                    </div>
                </div>

                <div className="form-group">
                    <label>Pickup Location</label>
                    <div style={{ position: 'relative' }}>
                        <MapPin size={18} style={{ position: 'absolute', top: '14px', left: '12px', color: 'var(--text-muted)' }} />
                        <input 
                            type="text" 
                            name="pickupLocation"
                            className="form-input" 
                            style={{ paddingLeft: '2.5rem' }}
                            placeholder="Where are you now?" 
                            value={formData.pickupLocation}
                            onChange={handleChange}
                            required 
                        />
                    </div>
                </div>

                <div className="form-group">
                    <label>Dropoff Location</label>
                    <div style={{ position: 'relative' }}>
                        <Navigation size={18} style={{ position: 'absolute', top: '14px', left: '12px', color: 'var(--text-muted)' }} />
                        <input 
                            type="text" 
                            name="dropoffLocation"
                            className="form-input" 
                            style={{ paddingLeft: '2.5rem' }}
                            placeholder="Where to?" 
                            value={formData.dropoffLocation}
                            onChange={handleChange}
                            required 
                        />
                    </div>
                </div>

                <button type="submit" className="btn" disabled={isSubmitting} style={{ marginTop: '1rem' }}>
                    {isSubmitting ? (
                        <>
                            <div className="spinner"></div> Processing...
                        </>
                    ) : (
                        'Confirm Booking'
                    )}
                </button>
            </form>
        </div>
    );
};

export default BookingForm;
