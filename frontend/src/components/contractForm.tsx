import { contractCreateSchema } from "@/app/hooks/useSchemas";
import { TextField, Button, Box, Typography } from "@mui/material";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { api as axios, handleAxiosError } from "../app/services/axios";
import { zodResolver } from "@hookform/resolvers/zod";
import { toast } from "react-toastify";

export const ContractForm = () => {
  const schema = contractCreateSchema;
  type SchemaProps = z.infer<typeof schema>;

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<SchemaProps>({
    resolver: zodResolver(schema),
  });

  const onSubmit = async (data: SchemaProps) => {
    try {
      await axios.post("/contract/create", data, {
        withCredentials: true,
      });

      toast.success("Contrato criado com sucesso!");
    } catch (error) {
      toast.error(handleAxiosError(error));
    }
  };

  return (
    <>
      <Box
        component="form"
        onSubmit={handleSubmit(onSubmit)}
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: 3,
          width: "100%",
          margin: "0 auto",
        }}
      >
        <Typography variant="h6" gutterBottom>
          Dados do contratado
        </Typography>

        <TextField
          label="Nome"
          {...register("contractor.name")}
          error={!!errors.contractor?.name}
          helperText={errors.contractor?.name?.message}
          placeholder="Digite seu nome"
          required
          fullWidth
        />

        <TextField
          label="CPF / CNPJ"
          {...register("contractor.cpfCnpj")}
          error={!!errors.contractor?.cpfCnpj}
          helperText={errors.contractor?.cpfCnpj?.message}
          placeholder="Digite seu cpf / cnpj"
          required
          fullWidth
        />

        <TextField
          label="Email"
          {...register("contractor.email")}
          error={!!errors.contractor?.email}
          helperText={errors.contractor?.email?.message}
          placeholder="Digite seu email"
          required
          fullWidth
        />

        <Typography variant="h6" gutterBottom>
          Dados do contratante
        </Typography>

        <TextField
          label="Nome"
          {...register("contractee.name")}
          error={!!errors.contractee?.name}
          helperText={errors.contractee?.name?.message}
          placeholder="Digite seu nome"
          required
          fullWidth
        />

        <TextField
          label="CPF / CNPJ"
          {...register("contractee.cpfCnpj")}
          error={!!errors.contractee?.cpfCnpj}
          helperText={errors.contractee?.cpfCnpj?.message}
          placeholder="Digite seu cpf / cnpj"
          required
          fullWidth
        />

        <TextField
          label="Email"
          {...register("contractee.email")}
          error={!!errors.contractee?.email}
          helperText={errors.contractee?.email?.message}
          placeholder="Digite seu email"
          required
          fullWidth
        />

        <Typography variant="h6" gutterBottom>
          Dados do contrato
        </Typography>

        <TextField
          label="Valor do contrato"
          type="number"
          {...register("contract.amount", { valueAsNumber: true })}
          error={!!errors.contract?.amount}
          helperText={errors.contract?.amount?.message}
          placeholder="Digite o valor"
          required
          fullWidth
        />

        <TextField
          label="Data de início"
          type="date"
          InputLabelProps={{ shrink: true }}
          {...register("contract.startDate")}
          error={!!errors.contract?.startDate}
          helperText={errors.contract?.startDate?.message}
          required
          fullWidth
        />

        <TextField
          label="Duração (em meses)"
          type="number"
          {...register("contract.duration", { valueAsNumber: true })}
          error={!!errors.contract?.duration}
          helperText={errors.contract?.duration?.message}
          placeholder="Ex: 12"
          required
          fullWidth
        />

        <TextField
          label="Cidade/Uf"
          {...register("contract.cityUf")}
          error={!!errors.contract?.cityUf}
          helperText={errors.contract?.cityUf?.message}
          placeholder="Digite a cidade/uf"
          required
          fullWidth
        />

        <TextField
          label="Descrição"
          multiline
          rows={4}
          {...register("contract.description")}
          error={!!errors.contract?.description}
          helperText={errors.contract?.description?.message}
          placeholder="Descreva os detalhes do contrato"
          required
          fullWidth
        />

        <Button type="submit" variant="contained" color="primary">
          Salvar
        </Button>
      </Box>
    </>
  );
};
