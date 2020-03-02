package pl.zhr.hak.wykrywaczchorob;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {
    static class PatientViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewName;
        private final TextView textViewDisease;
        private final CheckBox checkBoxDelete;

        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewName);
            this.textViewDisease = itemView.findViewById(R.id.textViewDisease);
            this.checkBoxDelete = itemView.findViewById(R.id.checkBoxDelete);
        }
    }

    private int DISEASE_COUNTER = 5;
    private List<Patient> mPatientList;
    private final LayoutInflater mInflater;
    private Context mContext;
    private List<Patient> patientsToDelete = new ArrayList<>();
    public PatientAdapter(List<Patient> patientList, Context context) {
        this.mPatientList = patientList;
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.listitem_patient, parent, false);
        return new PatientViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
        String [] diseaseList = new String [DISEASE_COUNTER + 1];
        setDiseases(diseaseList);
        holder.textViewName.setText(mContext.getString(R.string.patient,
                position + 1,
                mPatientList.get(position).getName(),
                mPatientList.get(position).getSurname()));
        holder.textViewDisease.setText(diseaseList[mPatientList.get(position).getDiseaseID()]);
        holder.checkBoxDelete.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                patientsToDelete.add(mPatientList.get(position));
            }
            else {
                patientsToDelete.remove(mPatientList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() { return mPatientList.size(); }

    void setPatients(List<Patient> patients) {
        mPatientList = patients;
        notifyDataSetChanged();
    }

    public void setDiseases(String [] diseases) {
        diseases[1] = mContext.getString(R.string.coronavirus);
        diseases[2] = mContext.getString(R.string.food_poisoning);
        diseases[3] = mContext.getString(R.string.flu);
        diseases[4] = mContext.getString(R.string.angina);
        diseases[5] = mContext.getString(R.string.hypochondria);
    }

    public void deleteSelected(PatientViewModel patientViewModel) {
        for (Patient patient : patientsToDelete) {
            patientViewModel.delete(patient);
        }
    }
}

