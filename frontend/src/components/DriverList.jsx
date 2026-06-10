import { useEffect, useState } from 'react';
import { Car, User } from 'lucide-react';
import { driverService } from '../services/api';

const DriverList = () => {
    const [drivers, setDrivers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchDrivers = async () => {
            try {
                const data = await driverService.getAvailableDrivers();
                setDrivers(data);
                setLoading(false);
            } catch (err) {
                console.error("Failed to fetch drivers:", err);
                setError("Failed to load available drivers.");
                setLoading(false);
            }
        };

        fetchDrivers();
    }, []);

    if (loading) {
        return (
            <div className="glass-panel" style={{ display: 'flex', justifyContent: 'center', padding: '3rem' }}>
                <div className="spinner"></div>
            </div>
        );
    }

    if (error) {
        return <div className="glass-panel" style={{ color: 'var(--danger)' }}>{error}</div>;
    }

    return (
        <div className="glass-panel">
            <h2 style={{ marginBottom: '1rem', display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
                <Car size={24} color="var(--primary)" />
                Available Drivers
            </h2>
            <p style={{ color: 'var(--text-muted)', marginBottom: '1.5rem', fontSize: '0.9rem' }}>
                These drivers are currently online and ready for a ride.
            </p>
            
            {drivers.length === 0 ? (
                <div style={{ textAlign: 'center', padding: '2rem', color: 'var(--text-muted)' }}>
                    No drivers available at the moment.
                </div>
            ) : (
                <div className="driver-grid">
                    {drivers.map((driver, index) => (
                        <div 
                            className="driver-card" 
                            key={driver.id}
                            style={{ animationDelay: `${index * 0.1}s` }}
                        >
                            <div className="driver-icon">
                                <User size={24} />
                            </div>
                            <div className="driver-name">{driver.name}</div>
                            <div className="driver-vehicle">{driver.vehicleModel}</div>
                            <div className="driver-vehicle" style={{ fontSize: '0.75rem', marginTop: '0.2rem', opacity: 0.8 }}>
                                {driver.vehicleNumber}
                            </div>
                            <span className="badge available">Available</span>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
};

export default DriverList;
